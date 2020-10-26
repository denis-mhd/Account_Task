package finalTask.account.services;

import finalTask.account.models.JsonDto.AccountJsonDto;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface JsonImportExportService {

    void exportJson() throws IOException;

    void importFromJson() throws FileNotFoundException;

    String seedAccountDto(AccountJsonDto[] accountJsonDtos);
}
