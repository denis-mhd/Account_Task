package finalTask.account.services.impl;

import com.google.gson.Gson;
import finalTask.account.models.Account;
import finalTask.account.models.Deposit;
import finalTask.account.models.HistoryOfTransactions;
import finalTask.account.models.JsonDto.AccountJsonDto;
import finalTask.account.models.Withdraw;
import finalTask.account.models.dto.AccountDto;
import finalTask.account.services.*;
import finalTask.account.util.FileUtil;
import finalTask.account.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static finalTask.account.constants.GlobalConstants.ACCOUNTS_FILE_OUTPUT_PATH;
import static finalTask.account.constants.GlobalConstants.ACCOUNTS_FILE_PATH;

@Service
public class JsonImportExportServiceImpl implements JsonImportExportService {
    private final HistoryOfTransactionService historyOfTransactionService;
    private final AccountService accountService;
    private final DepositService depositService;
    private final WithdrawService withdrawService;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    public JsonImportExportServiceImpl(HistoryOfTransactionService historyOfTransactionService,
                                       AccountService accountService, DepositService depositService,
                                       WithdrawService withdrawService, ValidatorUtil validatorUtil,
                                       ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.historyOfTransactionService = historyOfTransactionService;
        this.accountService = accountService;
        this.depositService = depositService;
        this.withdrawService = withdrawService;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public void exportJson() throws IOException {
        List<AccountDto> accountDtos = this.accountService.getAllAccounts();
        List<AccountJsonDto> accountJsonDtos = accountDtos.stream()
                .map(accountDto -> this.modelMapper.map(accountDto, AccountJsonDto.class))
                .collect(Collectors.toList());
        accountJsonDtos.forEach(e -> {

            e.setDateOfOpening(e.getDateOfOpening().replace("T", " "));
            e.setDateOfOpening(e.getDateOfOpening().substring(0, 19));
            e.getHistoryOfTransactions().getDeposits().forEach(d -> {
                d.setDateOfTransaction(d.getDateOfTransaction().replace("T", " "));
                d.setDateOfTransaction(d.getDateOfTransaction().substring(0, 19));
            });
            e.getHistoryOfTransactions().getWithdraws().forEach(w -> {
                w.setDateOfTransaction(w.getDateOfTransaction().replace("T", " "));
                w.setDateOfTransaction(w.getDateOfTransaction().substring(0, 19));
            });
        });
        String json = this.gson.toJson(accountJsonDtos);
        this.fileUtil.write(json, ACCOUNTS_FILE_OUTPUT_PATH);
    }



    @Override
    public void importFromJson() throws FileNotFoundException {
        AccountJsonDto[] dtos = this.gson.fromJson(new FileReader(ACCOUNTS_FILE_PATH), AccountJsonDto[].class);
        seedAccountDto(dtos);
    }

    @Override
    public String seedAccountDto(AccountJsonDto[] accountJsonDtos) {
        StringBuilder message = new StringBuilder();

        Arrays.stream(accountJsonDtos).forEach(accountJsonDto -> {
            if (this.validatorUtil.isValid(accountJsonDto)) {
                if(this.accountService.findAccountByNumberWithoutException(accountJsonDto.getAccountNumber()) == null){
                    Account account = this.modelMapper.map(accountJsonDto, Account.class);
                    account.setDateOfOpening(dateParser(accountJsonDto.getDateOfOpening()));
                    HistoryOfTransactions historyOfTransactions = this.modelMapper
                            .map(accountJsonDto.getHistoryOfTransactions(), HistoryOfTransactions.class);
                    this.historyOfTransactionService.saveOperation(historyOfTransactions);
                    account.setHistoryOfTransactions(historyOfTransactions);
                    this.accountService.save(account);
                    historyOfTransactions.setAccount(account);

                    accountJsonDto.getHistoryOfTransactions().getDeposits().forEach(
                            depositJsonDto -> {
                                Deposit deposit = this.modelMapper.map(depositJsonDto, Deposit.class);
                                deposit.setAccount(account);
                                deposit.setDateOfTransaction(dateParser(depositJsonDto.getDateOfTransaction()));
                                deposit.setHistoryOfTransactions(historyOfTransactions);
                                this.depositService.saveOperation(deposit);
                            }
                    );
                    accountJsonDto.getHistoryOfTransactions().getWithdraws().forEach(
                            withdrawJsonDto -> {
                                Withdraw withdraw = this.modelMapper.map(withdrawJsonDto, Withdraw.class);
                                withdraw.setAccount(account);
                                withdraw.setDateOfTransaction(dateParser(withdrawJsonDto.getDateOfTransaction()));
                                withdraw.setHistoryOfTransactions(historyOfTransactions);
                                this.withdrawService.saveOperation(withdraw);
                            }
                    );
                    this.historyOfTransactionService.saveOperation(historyOfTransactions);
                    this.accountService.saveOperation(account);

                }else {
                    message.append("Account is already in database.");
                }

            } else {
                this.validatorUtil.violations(accountJsonDto).stream().map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }
        });
        return message.toString();
    }

    private LocalDateTime dateParser(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }
}
