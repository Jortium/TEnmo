package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AuditLogger {
    public void auditLog(String auditString) throws IOException {
        File auditText = new File("auditLog.txt");
        if(!auditText.exists()){
            auditText.createNewFile();
        }
        FileWriter aFileWriter = new FileWriter(auditText, true);
        Timestamp timestampNow = Timestamp.valueOf(LocalDateTime.now());
        aFileWriter.write(timestampNow + " " + auditString + "\n");
        aFileWriter.close();
    }
}
