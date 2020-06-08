package net.tenie.myblog.controller;

import java.io.IOException;
import java.io.OutputStream; 
import javax.servlet.http.HttpServletResponse;  
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;

import net.tenie.myblog.service.CecheResult;
 
/**
 * 下载配置文件
 *
 */
@Controller
@RequestMapping("/download")
public class DownloadController { 
		
		@RequestMapping("/toshiba")
        public void download(HttpServletResponse res) throws IOException {
            OutputStream os = res.getOutputStream();
            try {
                res.reset();
                res.setHeader("Content-Disposition", "attachment; filename=dict.ovpn");
                res.setContentType("application/octet-stream; charset=utf-8");
               
                String config = toshibaConfig  +
                				"remote "+
                				CecheResult.getIp() +
                				" 1194 \n"
                				;
                os.write(config.getBytes());
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }
		
		
		 
		private String tc_ip = 		"remote 114.84.172.81 1194\n" ;
		private String toshibaConfig = 	
				 "client\n" + 
				 "dev tun\n" + 
				 "proto udp\n" + 
				 "resolv-retry infinite\n" + 
				 "nobind\n" + 
				 "persist-key\n" + 
				 "persist-tun\n" + 
				 "<ca>\n" + 
				 "-----BEGIN CERTIFICATE-----\n" + 
				 "MIIDNTCCAh2gAwIBAgIJAMrYdhrWxnNKMA0GCSqGSIb3DQEBCwUAMBYxFDASBgNV\n" + 
				 "BAMMC0Vhc3ktUlNBIENBMB4XDTE5MTIwMjEyNTc1MloXDTI5MTEyOTEyNTc1Mlow\n" + 
				 "FjEUMBIGA1UEAwwLRWFzeS1SU0EgQ0EwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAw\n" + 
				 "ggEKAoIBAQDbUDiobIn0eSumppo950l+A1P8QF3S62d7h4yWKjpwZhT0redBUgRn\n" + 
				 "iqU26VdJT9EuQtXmpfpS5UTlCD7zDOtSh1uNenlvj6JkEFcekHtP4ExQETtSsPB5\n" + 
				 "9WbKK4zEgJHdlpO63N7SzxTb7hIriPnkfyDia/FLjq1198G51OR88bBWCh0bHIHb\n" + 
				 "1XHlsf/QVEAvC1oE+fspWyJSnUwOprCM+/QdL+WVpdRPl//1ZjbQyjp7Qk0cG7aI\n" + 
				 "kb25fhdvG9j3pMNtm6Wiuz+ciy15RrRldFlh3lBLHBOKjWNwGeF85q3eOZA0JVBz\n" + 
				 "mlemjvz4lBQc15EoES7kGtq1ypdmivIBAgMBAAGjgYUwgYIwHQYDVR0OBBYEFNlY\n" + 
				 "1r7Fl3RS41M0ROlr5lumrP3JMEYGA1UdIwQ/MD2AFNlY1r7Fl3RS41M0ROlr5lum\n" + 
				 "rP3JoRqkGDAWMRQwEgYDVQQDDAtFYXN5LVJTQSBDQYIJAMrYdhrWxnNKMAwGA1Ud\n" + 
				 "EwQFMAMBAf8wCwYDVR0PBAQDAgEGMA0GCSqGSIb3DQEBCwUAA4IBAQBMbEDOdtE2\n" + 
				 "3qX4Kjg0+mKVR1/OKaKG/YlVFlfxacVr2bllyUZi9ytlVke/sYu3w9+un3NXeX6/\n" + 
				 "0NVoQBRUZRC94rcj9fk+A/3KCHlnkKxoASVYejNS/syYFnRGufm7LLilJe0uFxsc\n" + 
				 "OYK14D/jvETWP+XAgnFa/c513PSp8uLQR2oczxxDEuPCK5yFXCb5K42b4AkZ2yx/\n" + 
				 "ZQPe+X66SaQBbRGvTOBuL6k00AXEcEfBFVovy7L1WfrJr6ib1zv4MDqDxyoHAVh5\n" + 
				 "YkjeItlZbUwVPYofIZK87HvBB0XXv1fvJ9P1TGJGKAQEl7AlqkEyoKipMmYb5VF0\n" + 
				 "Pp+1j4MYSIfI\n" + 
				 "-----END CERTIFICATE-----\n" + 
				 "</ca>\n" + 
				 "<cert>\n" + 
				 "Certificate:\n" + 
				 "    Data:\n" + 
				 "        Version: 3 (0x2)\n" + 
				 "        Serial Number:\n" + 
				 "            f7:b4:e1:a7:52:91:29:e4:af:b9:9b:19:af:aa:1f:ff\n" + 
				 "    Signature Algorithm: sha256WithRSAEncryption\n" + 
				 "        Issuer: CN=Easy-RSA CA\n" + 
				 "        Validity\n" + 
				 "            Not Before: Apr 13 09:54:27 2020 GMT\n" + 
				 "            Not After : Mar 29 09:54:27 2023 GMT\n" + 
				 "        Subject: CN=ip8\n" + 
				 "        Subject Public Key Info:\n" + 
				 "            Public Key Algorithm: rsaEncryption\n" + 
				 "                Public-Key: (2048 bit)\n" + 
				 "                Modulus:\n" + 
				 "                    00:af:00:ac:2a:3f:74:c9:0a:63:74:f4:ca:25:da:\n" + 
				 "                    e8:12:c4:18:68:1f:6b:07:d2:91:c1:04:d5:17:bf:\n" + 
				 "                    cc:7b:43:3d:98:74:72:13:fd:45:5a:7c:d8:c8:e4:\n" + 
				 "                    de:89:38:49:61:12:45:26:86:69:3c:1d:7a:68:ce:\n" + 
				 "                    2b:13:9d:82:a0:dd:df:df:0d:59:99:4a:7a:1d:e2:\n" + 
				 "                    53:9a:8a:25:96:d3:34:f5:1c:2a:cf:80:ca:bb:03:\n" + 
				 "                    6e:62:3f:48:2f:50:ec:11:e8:dd:0d:dc:dc:fb:13:\n" + 
				 "                    81:de:d6:f2:34:6b:89:b6:28:a4:bd:57:e1:5f:ca:\n" + 
				 "                    2f:3c:d4:34:66:ac:3a:c3:14:9f:07:24:9c:05:7e:\n" + 
				 "                    30:22:ec:11:d0:40:e4:35:09:73:c3:80:e5:cf:20:\n" + 
				 "                    be:56:fe:4a:1a:d2:60:bb:bb:90:2d:b1:2a:4f:5b:\n" + 
				 "                    ea:f6:7a:a0:82:e3:76:f7:eb:fa:a4:2f:2c:b2:d1:\n" + 
				 "                    98:37:a8:ba:68:10:d4:4e:cb:c3:79:4c:53:8b:a6:\n" + 
				 "                    bf:f6:e8:13:4f:69:c7:3e:f2:00:e5:fe:68:59:00:\n" + 
				 "                    3d:f3:0d:2f:56:e7:3e:e1:6e:47:a6:49:4d:79:e6:\n" + 
				 "                    11:6b:63:72:18:09:03:ca:39:83:d1:04:e3:be:ae:\n" + 
				 "                    d3:25:13:2b:56:a9:31:5e:8b:f1:12:b0:07:99:0d:\n" + 
				 "                    1c:4b\n" + 
				 "                Exponent: 65537 (0x10001)\n" + 
				 "        X509v3 extensions:\n" + 
				 "            X509v3 Basic Constraints: \n" + 
				 "                CA:FALSE\n" + 
				 "            X509v3 Subject Key Identifier: \n" + 
				 "                55:46:85:F1:8A:86:ED:B1:30:45:06:21:3D:19:30:1E:8D:14:DB:83\n" + 
				 "            X509v3 Authority Key Identifier: \n" + 
				 "                keyid:D9:58:D6:BE:C5:97:74:52:E3:53:34:44:E9:6B:E6:5B:A6:AC:FD:C9\n" + 
				 "                DirName:/CN=Easy-RSA CA\n" + 
				 "                serial:CA:D8:76:1A:D6:C6:73:4A\n" + 
				 "\n" + 
				 "            X509v3 Extended Key Usage: \n" + 
				 "                TLS Web Client Authentication\n" + 
				 "            X509v3 Key Usage: \n" + 
				 "                Digital Signature\n" + 
				 "            Netscape Comment: \n" + 
				 "                Easy-RSA (v3.0.6) Generated Certificate\n" + 
				 "            Netscape Cert Type: \n" + 
				 "                SSL Client\n" + 
				 "    Signature Algorithm: sha256WithRSAEncryption\n" + 
				 "         d2:75:fa:bd:77:73:fb:94:16:61:10:e6:04:86:fa:96:70:c6:\n" + 
				 "         41:4c:c0:50:77:e4:d1:99:a8:2a:40:16:25:f0:fb:7b:a3:90:\n" + 
				 "         50:ee:c5:57:89:8a:e4:8c:98:cd:34:73:aa:1c:c5:7b:67:76:\n" + 
				 "         9f:fc:d7:6e:ae:2a:c0:cd:7c:17:d5:9a:3d:a8:46:58:12:92:\n" + 
				 "         dc:9b:8e:b8:04:f5:ce:d6:97:c1:0e:7a:e8:fe:45:5c:da:a7:\n" + 
				 "         f5:d5:40:e5:43:78:02:6d:ee:5c:93:33:5e:99:18:ce:4d:72:\n" + 
				 "         5b:aa:4f:98:60:2f:e1:59:a2:e9:1a:c4:47:5f:4f:61:c9:0e:\n" + 
				 "         6d:c4:95:a5:e1:49:56:3a:1c:3f:72:b5:25:48:e6:71:97:8f:\n" + 
				 "         36:25:74:01:0d:34:f0:65:4c:b8:4f:20:70:39:82:33:37:e2:\n" + 
				 "         af:d2:63:f5:88:1a:4f:51:73:b5:84:ec:59:d5:7b:95:45:1e:\n" + 
				 "         f5:c6:77:dc:e9:f8:7e:43:d1:a8:19:e6:4d:b1:8b:b0:fe:d5:\n" + 
				 "         9a:9c:be:30:37:e0:d2:04:aa:4e:b7:89:3a:25:95:46:f6:3a:\n" + 
				 "         4d:e1:e7:32:f0:77:52:c8:72:34:ac:a5:9e:1b:0b:90:f5:80:\n" + 
				 "         ab:84:ef:5f:27:46:8b:9d:58:eb:ec:e0:be:d5:ba:97:50:c8:\n" + 
				 "         fc:e1:54:24\n" + 
				 "-----BEGIN CERTIFICATE-----\n" + 
				 "MIIDkjCCAnqgAwIBAgIRAPe04adSkSnkr7mbGa+qH/8wDQYJKoZIhvcNAQELBQAw\n" + 
				 "FjEUMBIGA1UEAwwLRWFzeS1SU0EgQ0EwHhcNMjAwNDEzMDk1NDI3WhcNMjMwMzI5\n" + 
				 "MDk1NDI3WjAOMQwwCgYDVQQDDANpcDgwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAw\n" + 
				 "ggEKAoIBAQCvAKwqP3TJCmN09Mol2ugSxBhoH2sH0pHBBNUXv8x7Qz2YdHIT/UVa\n" + 
				 "fNjI5N6JOElhEkUmhmk8HXpozisTnYKg3d/fDVmZSnod4lOaiiWW0zT1HCrPgMq7\n" + 
				 "A25iP0gvUOwR6N0N3Nz7E4He1vI0a4m2KKS9V+Ffyi881DRmrDrDFJ8HJJwFfjAi\n" + 
				 "7BHQQOQ1CXPDgOXPIL5W/koa0mC7u5AtsSpPW+r2eqCC43b36/qkLyyy0Zg3qLpo\n" + 
				 "ENROy8N5TFOLpr/26BNPacc+8gDl/mhZAD3zDS9W5z7hbkemSU155hFrY3IYCQPK\n" + 
				 "OYPRBOO+rtMlEytWqTFei/ESsAeZDRxLAgMBAAGjgeIwgd8wCQYDVR0TBAIwADAd\n" + 
				 "BgNVHQ4EFgQUVUaF8YqG7bEwRQYhPRkwHo0U24MwRgYDVR0jBD8wPYAU2VjWvsWX\n" + 
				 "dFLjUzRE6WvmW6as/cmhGqQYMBYxFDASBgNVBAMMC0Vhc3ktUlNBIENBggkAyth2\n" + 
				 "GtbGc0owEwYDVR0lBAwwCgYIKwYBBQUHAwIwCwYDVR0PBAQDAgeAMDYGCWCGSAGG\n" + 
				 "+EIBDQQpFidFYXN5LVJTQSAodjMuMC42KSBHZW5lcmF0ZWQgQ2VydGlmaWNhdGUw\n" + 
				 "EQYJYIZIAYb4QgEBBAQDAgeAMA0GCSqGSIb3DQEBCwUAA4IBAQDSdfq9d3P7lBZh\n" + 
				 "EOYEhvqWcMZBTMBQd+TRmagqQBYl8Pt7o5BQ7sVXiYrkjJjNNHOqHMV7Z3af/Ndu\n" + 
				 "rirAzXwX1Zo9qEZYEpLcm464BPXO1pfBDnro/kVc2qf11UDlQ3gCbe5ckzNemRjO\n" + 
				 "TXJbqk+YYC/hWaLpGsRHX09hyQ5txJWl4UlWOhw/crUlSOZxl482JXQBDTTwZUy4\n" + 
				 "TyBwOYIzN+Kv0mP1iBpPUXO1hOxZ1XuVRR71xnfc6fh+Q9GoGeZNsYuw/tWanL4w\n" + 
				 "N+DSBKpOt4k6JZVG9jpN4ecy8HdSyHI0rKWeGwuQ9YCrhO9fJ0aLnVjr7OC+1bqX\n" + 
				 "UMj84VQk\n" + 
				 "-----END CERTIFICATE-----\n" + 
				 "\n" + 
				 "</cert>\n" + 
				 "<key> \n" + 
				 "-----BEGIN PRIVATE KEY-----\n" + 
				 "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCvAKwqP3TJCmN0\n" + 
				 "9Mol2ugSxBhoH2sH0pHBBNUXv8x7Qz2YdHIT/UVafNjI5N6JOElhEkUmhmk8HXpo\n" + 
				 "zisTnYKg3d/fDVmZSnod4lOaiiWW0zT1HCrPgMq7A25iP0gvUOwR6N0N3Nz7E4He\n" + 
				 "1vI0a4m2KKS9V+Ffyi881DRmrDrDFJ8HJJwFfjAi7BHQQOQ1CXPDgOXPIL5W/koa\n" + 
				 "0mC7u5AtsSpPW+r2eqCC43b36/qkLyyy0Zg3qLpoENROy8N5TFOLpr/26BNPacc+\n" + 
				 "8gDl/mhZAD3zDS9W5z7hbkemSU155hFrY3IYCQPKOYPRBOO+rtMlEytWqTFei/ES\n" + 
				 "sAeZDRxLAgMBAAECggEALuiwPXba+BSnyi7fjLwb3wPfQvHA/8zKswF3mTEZP6Ur\n" + 
				 "epUkCwWltHaoqVjn6cJDsRhznrsCtFF2gWdaFpzu6e3vV1u9u6BCEYacQF1ta/tb\n" + 
				 "Q6R0OUDdlrl5ui4aTEyVqstPjaq5NTfqZPAa5kA5HrHQTsyipcmNVfHPxgG+kpGf\n" + 
				 "1ePUAgqaEwSf55lG1kJphklK4rhaDufYZdlkIIT0m7PnTvLOa9+bVJCsupY/w+5m\n" + 
				 "ta0uEVoeJ2tvNOzryvP+jgaMfu7DxORCJdCFNyliOkj9yKwnkLsNupX7DEx8RYN3\n" + 
				 "ZiOi90o/NABqaUNnqP5/vYjAVD2HlZSdUEcJfM3EaQKBgQDfq4rUxdtWzpKFQ9jX\n" + 
				 "EbA8d5utgRXn7y54a2NAUWlRpuv8SRRCIETfY9DdLwdDr6cwBrxvygvXJuL/q+8b\n" + 
				 "UsSMe1F2cznLqUOci4RjQrhGueUT8GsqGLDiIy93YQLHd8iWlY7Qcy8zMzCOVyI3\n" + 
				 "efpfuhM1QK3wFMvYLj4YOWq0tQKBgQDITEojurV8YBIZjSleEUZNvjE+XfASEA5R\n" + 
				 "Ow8XpNCwvQCuJorZZd42dXl+rPFyNnh447Goobju1Qae5q6JwOcmJqDEPULlvVHE\n" + 
				 "mlpwA9qFMAO+oN1x3sQmEkL8mNA+E7kPYaBx3GJ04T7BbUSh24ufLfY+Q9RZKB0O\n" + 
				 "/ATJ0mQs/wKBgQDc1l9901jMLatnHuUC/Dj4itPPjGtXk8VbKhRV1cvZzaikcHcr\n" + 
				 "DvdRZRk8uYk/oDe/aHc3HNNx+7gW3eh4HtFnLv7wp7YByr6x2uK8qoyNcE+ozzDP\n" + 
				 "4I8Mx9a+1K7MyQzo+QOH9qI8uCcR8yAajDnTuEHsy06xg6b8bDPs8QvVHQKBgQDD\n" + 
				 "S39ObxHdf16mgzi+e2FtE/C3uqgFNJqBIXCJwS2p5d7v7qybY/PSbaBUnxOcJPg6\n" + 
				 "BuiGX4kNda/K52lu7yNx02AVdxXvIzTC2S0s4tUB39sG15qTGH1wEYY2/Fiu7lQW\n" + 
				 "Zz4lRpjJFYY22u98UbBFOG9MLp6xf6c1oQyaLAV4yQKBgQCotB7VwMOcTwt/C6+i\n" + 
				 "h58qnQg12mJyYdFfbyt1CyPadbU3QbfJ63RCOJ3uUABu86CZLVv5s2I+sS0L4nhI\n" + 
				 "/X0Cz/ZuRdCLYLhxrrVnkUOeNP71uw4IkFpfZex8TQJAk56ju5U5bVPuTygSHooo\n" + 
				 "7oya2az3z8rrSKM2oBdhDBbx1A==\n" + 
				 "-----END PRIVATE KEY-----\n" + 
				 "</key>\n" + 
				 "cipher AES-256-CBC\n" + 
				 "comp-lzo\n" + 
				 "verb 3\n"  ;
				 
				 
		 
		 
}
