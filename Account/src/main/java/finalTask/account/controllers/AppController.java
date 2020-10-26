package finalTask.account.controllers;

import finalTask.account.models.dto.AccountDto;
import finalTask.account.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AppController implements CommandLineRunner {
    private final AccountService accountService;
    private final DepositService depositService;
    private final WithdrawService withdrawService;
    private final TransferService transferService;
    private final JsonImportExportService jsonTransferService;

    @Autowired
    public AppController(AccountService accountService, DepositService depositService,
                         WithdrawService withdrawService, TransferService transferService,
                         JsonImportExportService jsonTransferService) {
        this.jsonTransferService = jsonTransferService;
        this.accountService = accountService;
        this.depositService = depositService;
        this.withdrawService = withdrawService;
        this.transferService = transferService;
    }

    @Override
    public void run(String... args) throws Exception {

        // Creating 2 test accounts
       this.accountService.createAccount("1234", null, 5, "deposit");
       this.accountService.createAccount("1235", null, 5, "current");
       AccountDto sender = this.accountService.findAccountByNumber("1234");
       AccountDto recipient = this.accountService.findAccountByNumber("1235");

       // deposit amount to first account
       this.depositService.depositAmount(BigDecimal.valueOf(100), "1234");

        // withdraw amount from first account
       this.withdrawService.withdrawAmount("1234", BigDecimal.valueOf(50));
       // make transfer between accounts
       this.transferService.transfer(sender, BigDecimal.valueOf(40), recipient, null);

       //export Json format
       this.jsonTransferService.exportJson();

        //import from Json file
    //    this.jsonTransferService.importFromJson();



    }
}