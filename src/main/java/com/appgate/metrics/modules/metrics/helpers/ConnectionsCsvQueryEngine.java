package com.appgate.metrics.modules.metrics.helpers;

import com.appgate.metrics.modules.metrics.model.ConnectionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.appgate.metrics.modules.logs.constants.ConnectionStatusEnum.SUCCESS;

@Component
public class ConnectionsCsvQueryEngine {

    private static final String[] CONNECTIONS_FIELDS = new String[] {
            "username", "datetime", "userip", "serverip", "authtype",
            "status", "internal", "servername" };

    private static final String DATE_FORMAT_STORED = "yyyyMMdd HH:mm:ss";
    public static final String START_DATE = "1800-01-01";
    public static final String START_DATE_FORMAT = "yyyy-MM-dd";
    public static final String CONNECTIONS_CSV_FILENAME = "connections.csv";

    final ObjectMapper mapper = new ObjectMapper();

    public Boolean isKnown(final String key, final String value) throws IOException {
        return Objects.nonNull(getEntry(key, value));
    }

    public Boolean isInternalIp(String ipAddress) throws IOException {
        return Boolean.parseBoolean(Objects.requireNonNull(getEntry("userip", ipAddress)).getInternal());
    }

    public Date getLastDate(String type) throws FileNotFoundException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_STORED, Locale.getDefault());
        Date date = new SimpleDateFormat(START_DATE_FORMAT).parse(START_DATE);
        List<ConnectionDTO> tableData = getTableData();
        for (ConnectionDTO entry : tableData) {
            if(Objects.nonNull(entry) && formatter.parse(entry.getDatetime()).after(date)
                    && entry.getStatus().equalsIgnoreCase(type)) {
                date = formatter.parse(entry.getDatetime());
            }
        }
        return date;
    }

    private ConnectionDTO getEntry(
            final String key, final String value) throws IOException {
        List<ConnectionDTO> tableData = getTableData();
        String fieldValue = "";
        for (ConnectionDTO entry : tableData) {
            switch (key) {
                case "username":
                    fieldValue = entry.getUsername();
                    break;
                case "userip":
                    fieldValue = entry.getUserip();
                    break;
                case "serverip":
                    fieldValue = entry.getServerip();
                    break;
                default:
            }
            if (fieldValue.equalsIgnoreCase(value)
                    && SUCCESS.name().equalsIgnoreCase(entry.getStatus()))
                return entry;
        }
        return null;
    }

    private List<ConnectionDTO> getTableData() throws FileNotFoundException {
        HashMap<String, String> dataEntry = new HashMap<>();
        List<ConnectionDTO> entriesFile = new ArrayList<>();
        File myObj = new File(CONNECTIONS_CSV_FILENAME);
        Scanner myReader = new Scanner(myObj);
        String[] data;
        while (myReader.hasNextLine()) {
            data = myReader.nextLine().split(",");
            if(data.length == CONNECTIONS_FIELDS.length) {
                for (int i = 0; i < CONNECTIONS_FIELDS.length; i++) {
                    dataEntry.put(CONNECTIONS_FIELDS[i], data[i]);
                }
                entriesFile.add(mapConnectionObject(dataEntry));
            }
        }
        myReader.close();
        return entriesFile;
    }

    private ConnectionDTO mapConnectionObject(HashMap<String, String> mapObject) {
        return mapper.convertValue(mapObject, ConnectionDTO.class);
    }
}
