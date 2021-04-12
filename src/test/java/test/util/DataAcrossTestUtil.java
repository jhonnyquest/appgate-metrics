package test.util;

import com.appgate.metrics.crosscutting.constants.ResponseCodeEnum;
import com.appgate.metrics.crosscutting.model.SimpleResponseDTO;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Mock data provider for unit tests
 *
 * @author blanclabs
 * @version 1.0
 * @since 2019-06-19
 */
public class DataAcrossTestUtil {

    public DataAcrossTestUtil() {
        super();
    }

    public MultipartFile getMultipartSourceFileMock() {
        return new MockMultipartFile("data",
                "filename.txt",
                "text/plain",
                getSourceText().getBytes());
    }

    private String getSourceText() {
        return "20140616 19:06:36 vm4 [4f8a7f8f:533e59ca] sshd Client protocol 2.0; client software version libssh2_1.4.2; AppGate version 10.2.2dev_2014032715\n" +
                "20140616 19:06:39 vm4 [4f8a7f8f:533e59ca] sshd transport algorithms: direction=client->server cipher=aes128-ctr mac=hmac-sha1 compression=none\n" +
                "20140616 19:06:39 vm4 [4f8a7f8f:533e59ca] sshd transport algorithms: direction=server->client cipher=aes128-ctr mac=hmac-sha1 compression=none\n" +
                "20140616 19:06:40 vm4 [4f8a7f8f:533e59c7] ag_userd Client exited.\n" +
                "20140616 19:06:40 vm4 [4f8a7f8f:533e59ca] ag_userd Looking for account source for 'root'\n" +
                "20140616 19:06:40 vm4 [4f8a7f8f:533e59ca] ag_userd User 'root' was not found\n" +
                "20140616 19:06:40 vm4 [4f8a7f8f:533e59ca] ag_userd 'root' not found in any account source\n" +
                "20140616 19:06:40 vm4 [4f8a7f8f:533e59ca] sshd policy_login: unknown user 'root'\n" +
                "20140616 19:06:40 vm4 [4f8a7f8f:533e59ca] sshd input_userauth_request: invalid user root\n" +
                "20140616 19:06:40 vm4 [4f8a7f8f:533e59ca] sshd Failed none for invalid user root from 61.174.50.177 port 2744 ssh2\n" +
                "20140616 19:06:40 vm4 [4f8a7f8f:533e59ca] sshd Connection closed by 61.174.50.177\n" +
                "20140616 19:06:40 vm4 [4f8a7f8f:533e59ca] sshd Connection closed by 61.174.50.177\n" +
                "20140616 19:06:40 vm4 [4f8a7f8f:533e59ca] SUSPECT blocked login root from 61.174.50.177 (none)\n" +
                "20140616 19:06:51 vm4 [4f8a7f8f:533e59ca] ag_userd Client exited.\n" +
                "20140616 19:07:12 vm4 [4f8a7f8f:533e59cd] connect to port 22 from 61.174.50.177\n" +
                "sshd transport algorithms: direction=client->server cipher=aes128-ctr mac=hmac-md5 compression=zlib\n" +
                "20140616 09:05:03 vm5 [4f8a7f94:533e229f] sshd transport algorithms: direction=server->client cipher=aes128-ctr mac=hmac-md5 compression=zlib\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] ag_userd Looking for account source for 'nikhil'\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] ag_userd Auth found by sdb(Local Accounts) for id='nikhil' search user='nikhil': unix=*temporary*, ext=nikhil, domain=*none*, fixedip=*none*, roles=administrator-role, auth=password\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] sshd Client was assigned temporary user: tu_55000\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] sshd Accepted password for nikhil from 112.196.12.67 port 57726 ssh2\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] login nikhil from 112.196.12.67 (password)\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] user nikhil available roles: administrator-role\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'login.client_ip' = '112.196.12.67'\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'login.client_name' = '112.196.12.67'\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'login.server_ip' = '79.138.127.148'\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'login.server_name' = 'vm5.appgate.com'\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'login.authentication' = 'password'\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'login.account_source' = 'Local Accounts'\n" +
                "20140616 09:05:09 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'login.role_membership' = 'administrator-role'\n" +
                "20140616 09:05:11 vm5 [4f8a7f94:533e229f] start fdclnt\n" +
                "20140616 09:05:11 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'login.protocol_version' = '21'\n" +
                "20140616 09:05:11 vm5 [4f8a7f94:533e229f] agsh Client information: 10.2.2dev_2014032715, Mac OS X/x86_64/10.9.2, Oracle Corporation/1.7.0_55, 4\n" +
                "20140616 09:05:11 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'client.java' = 'Oracle Corporation/1.7.0_55'\n" +
                "20140616 09:05:11 vm5 [4f8a7f94:533e229f] agsh Client information: Console 10.2.2dev_2014032715\n" +
                "20140616 09:05:12 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'client.iptunneling' = 'false'\n" +
                "20140616 09:05:12 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'client.platform' = 'unix.macosx.'\n" +
                "20140616 09:05:12 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'client.version' = '10.2.2dev_2014032715'\n" +
                "20140616 09:05:12 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'client.identd' = 'false'\n" +
                "20140616 09:05:12 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'client.lmhosts' = 'false'\n" +
                "20140616 09:05:12 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'client.firewall' = 'false'\n" +
                "20140616 09:05:12 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'client.type' = 'console'\n" +
                "20140616 09:05:12 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'client.webstart' = 'true'\n" +
                "20140616 09:05:12 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'client.mud' = 'false'\n" +
                "20140616 09:05:12 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'client.hosts' = 'false'\n" +
                "20140616 09:05:13 vm5 [4f8a7f94:533e229f] user nikhil selected role administrator-role\n" +
                "20140616 09:05:13 vm5 [4f8a7f94:533e229f] ag_stated Access rule 'always' evaluated to true\n" +
                "20140616 09:05:13 vm5 [4f8a7f94:533e229f] ag_stated Client attribute: 'login.role' = 'administrator-role'\n" +
                "20140616 09:05:13 vm5 [4f8a7f94:533e229f] Client info: 21 on unix\n" +
                "20140616 09:05:14 vm5 [4f8a7f94:533e229f] enabled L port-fwd to localhost:2076\n" +
                "20140616 09:06:08 vm5 [4f8a7f94:533e22a3] connect to port 22 from 112.196.12.67";
    }

    public File getTestFile() throws URISyntaxException {
        String fileName = "testFile.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }
        return new File(resource.toURI());
    }

    public SimpleResponseDTO getSimpleResponse(ResponseCodeEnum type) {
        return SimpleResponseDTO.builder()
                .success((type.equals(ResponseCodeEnum.SUCCESS)) ? Boolean.TRUE : Boolean.FALSE)
                .message(type.getDescription())
                .responseCode(type.getValue())
                .build();
    }
}
