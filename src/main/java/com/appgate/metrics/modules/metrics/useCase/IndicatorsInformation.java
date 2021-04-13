package com.appgate.metrics.modules.metrics.useCase;

import com.appgate.metrics.modules.metrics.helpers.ConnectionsCsvQueryEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static com.appgate.metrics.modules.logs.constants.ConnectionStatusEnum.FAILED;
import static com.appgate.metrics.modules.logs.constants.ConnectionStatusEnum.SUCCESS;

@Component
public class IndicatorsInformation implements IMetrics{

    @Autowired
    private ConnectionsCsvQueryEngine queryEngine;

    @Override
    public Boolean isUserKnown(final String userName) throws IOException {
        return queryEngine.isKnown("username", userName);
    }

    @Override
    public Boolean isIpKnown(final String ipAddress) throws IOException {
        return queryEngine.isKnown("userip", ipAddress) || queryEngine.isKnown("serverip", ipAddress);
    }

    @Override
    public Boolean isIPInternal(final String ipAddress) throws IOException {
        return queryEngine.isInternalIp(ipAddress);
    }

    @Override
    public Date getLastSuccessfulLoginDate(final String userName) throws FileNotFoundException, ParseException {
        return queryEngine.getLastDate(SUCCESS.name());
    }

    @Override
    public Date getLastFailedLoginDate(final String userName) throws FileNotFoundException, ParseException {
        return queryEngine.getLastDate(FAILED.name());
    }
}
