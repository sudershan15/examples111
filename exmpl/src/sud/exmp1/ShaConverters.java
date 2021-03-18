package sud.exmp1;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ShaConverters {

	public static void main(String[] args) {
		final String accessToken = "2q9l95mh60jbuf2q3tp0cgjci";
        final String macKey = "879uvoiydrcuywkmd334lutzi";
        final String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        final String nonce = new BigInteger(128, new SecureRandom()).toString(36);
        final String requestUri = "service/customreport2/super/SuperUser/Data_Audit_-_Worker_Details";
        final String hostname = "localhost";
        final int port = 11020;
        final String requestString = timestamp + "\n" + nonce + "\nGET\n/ccx/" + requestUri + "\n" + hostname
        		+ "\n" + port
        		+ "\n\n";

        
        final String xml = "<saml:Assertion xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\"\n" + 
        		"    xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n" + 
        		"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" + 
        		"    xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" Version=\"2.0\"\n" + 
        		"    ID=\"pfxb382fdb5-a1aa-1a5c-888f-fb50f6452179\" IssueInstant=\"2020-04-10T12:39:40Z\">\n" + 
        		"    <saml:Issuer>https://app.onelogin.com/saml/metadata/4b812e9c-dc27-45b1-9769-15e4635c7bbb</saml:Issuer>\n" + 
        		"    <ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n" + 
        		"        <ds:SignedInfo>\n" + 
        		"            <ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>\n" + 
        		"            <ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/>\n" + 
        		"            <ds:Reference URI=\"#pfxb382fdb5-a1aa-1a5c-888f-fb50f6452179\">\n" + 
        		"                <ds:Transforms>\n" + 
        		"                    <ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n" + 
        		"                    <ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>\n" + 
        		"                </ds:Transforms>\n" + 
        		"                <ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" + 
        		"                <ds:DigestValue>uujZ/SGcciPJoBdqW+cL3f3JMfsthAurxEL/ArFU+ek=</ds:DigestValue>\n" + 
        		"            </ds:Reference>\n" + 
        		"        </ds:SignedInfo>\n" + 
        		"        <ds:SignatureValue>0/DendgU+AvRCupV/upB2Bs0NoVW12PXtBPzgMjQvE825cSHHAFemQtwFBn4q+QktIKMRPO+tVA06IbIB261ajq2Kw/6xTZvPvkXkweML/A5lD1RC0tZ2Aob/s+NtMzHgaM3cbeOtGZuU8f8v9oXluBFC1fFP5m+v8VLHX4N1VvqOPKPd4Kd3wPxmbdNwivkzDuIiie/GZjoEuZ1SD/EGADPF9z4Wa2KusEMXc78KmQ5TZG+WUM7U9Um8Fp/01So7u3NBe5XRf59Ycon6YnO6OFjC6omGCVxQ0A1fNLW55IXox7Ud63Zqh/6a/LrV4hNyfXSqcUbdy/RwYEvb+XY1w==</ds:SignatureValue>\n" + 
        		"        <ds:KeyInfo>\n" + 
        		"            <ds:X509Data>\n" + 
        		"                <ds:X509Certificate>MIIEFzCCAv+gAwIBAgIUfFaB8QLXwNBAGwQItYxACzCm4bswDQYJKoZIhvcNAQEFBQAwWDELMAkGA1UEBhMCVVMxEDAOBgNVBAoMB1dvcmtkYXkxFTATBgNVBAsMDE9uZUxvZ2luIElkUDEgMB4GA1UEAwwXT25lTG9naW4gQWNjb3VudCAxMjkyOTUwHhcNMTgwNjIwMDg0MDMzWhcNMjMwNjIwMDg0MDMzWjBYMQswCQYDVQQGEwJVUzEQMA4GA1UECgwHV29ya2RheTEVMBMGA1UECwwMT25lTG9naW4gSWRQMSAwHgYDVQQDDBdPbmVMb2dpbiBBY2NvdW50IDEyOTI5NTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAO0CObbUmYKAngsMRO0QAQxLi/CpOW+gd5k/qlNbBbavLvsbzpyXApY1IAsqdtRKWGZj3s4fU0vId3MjyaXGSA9SRT4nqYHq7OsvfD8hGbF+LUDbiKzysXvnzc5ALpEwrzuZoPEA4LwUz/vUmU8d8aYHLterJ/biu5Uiw8TpjiOL0SwM6j6WASimKDT7KnvNqs88tSieLmLEiRvPeyJuQVzoilJbxw0b7UEOggN/eQEwtwHlumGCtcyGukNB0Fa+R6aXpDIu2kkPjs3E+VHTZQNhwSlqup5WWca2B+0Z33O6PTV7csXQhA6ujx59owH2cTR+FmXKgxwhWlbkdYqzFwkCAwEAAaOB2DCB1TAMBgNVHRMBAf8EAjAAMB0GA1UdDgQWBBTAgcesOGU+9WtJfGU5K+CQbs+aIjCBlQYDVR0jBIGNMIGKgBTAgcesOGU+9WtJfGU5K+CQbs+aIqFcpFowWDELMAkGA1UEBhMCVVMxEDAOBgNVBAoMB1dvcmtkYXkxFTATBgNVBAsMDE9uZUxvZ2luIElkUDEgMB4GA1UEAwwXT25lTG9naW4gQWNjb3VudCAxMjkyOTWCFHxWgfEC18DQQBsECLWMQAswpuG7MA4GA1UdDwEB/wQEAwIHgDANBgkqhkiG9w0BAQUFAAOCAQEAjAcL82SMpuuG19fNb06qREAR93EOhLzdIWv1Qbju50M9ntEc0imyXkGjfarRAxQarC+16q1DIM6GncA/f5zEvMgcaqTrUo55GiT3xApJdcqkxUQT9hu6uo2W99Fn0o54M8ZolPsqM9p8rJbuhAwnbwJuTHuqkSON+vIqciB7rG4HMi4hW0RiqkxJdf1kIlEpOou9erzKY879XMrrOnLxtryOhq2bJgxv6ReKmy8GqsC5Z3uebDk4LqoXj/8m2SZO20vg9rTpUzQsG9mO42B5rUEQTeb+wJtH1Djx1ad7ABNm2mHXZMbUbZDZtxXsY0NbnqWZThcVNGo+0slEIz247g==</ds:X509Certificate>\n" + 
        		"            </ds:X509Data>\n" + 
        		"        </ds:KeyInfo>\n" + 
        		"    </ds:Signature>\n" + 
        		"    <saml:Subject>\n" + 
        		"        <saml:NameID Format=\"urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress\"\n" + 
        		"            >bliu</saml:NameID>\n" + 
        		"        <saml:SubjectConfirmation Method=\"urn:oasis:names:tc:SAML:2.0:cm:bearer\">\n" + 
        		"            <saml:SubjectConfirmationData NotOnOrAfter=\"2020-04-10T12:42:40Z\"\n" + 
        		"                Recipient=\"http://workday.com\"/>\n" + 
        		"        </saml:SubjectConfirmation>\n" + 
        		"    </saml:Subject>\n" + 
        		"    <saml:Conditions NotBefore=\"2020-04-10T12:36:40Z\" NotOnOrAfter=\"2020-04-10T13:52:40Z\">\n" + 
        		"        <saml:AudienceRestriction>\n" + 
        		"            <saml:Audience>http://workday.com</saml:Audience>\n" + 
        		"        </saml:AudienceRestriction>\n" + 
        		"    </saml:Conditions>\n" + 
        		"    <saml:AuthnStatement AuthnInstant=\"2020-04-10T12:39:39Z\"\n" + 
        		"        SessionNotOnOrAfter=\"2020-04-11T12:39:40Z\"\n" + 
        		"        SessionIndex=\"_1a97d9c0-5c8d-0138-8616-2f4bf1b0b9a5\">\n" + 
        		"        <saml:AuthnContext>\n" + 
        		"            <saml:AuthnContextClassRef>urn:oasis:names:tc:SAML:2.0:ac:classes:PasswordProtectedTransport</saml:AuthnContextClassRef>\n" + 
        		"        </saml:AuthnContext>\n" + 
        		"    </saml:AuthnStatement>\n" + 
        		"</saml:Assertion>\n";
        final String code_verifier = "qwertyuiopasdfghjklzxcvbnmqwer1234567800-._~qwertyuiopasdfghjk";
        final String[] text = {" ", "NONE", "none", "nothing", "NOTHING", "NULL", "EMPTY", "RT", "REFRESH_TOKEN", "TOKEN", "refresh_token", "token",
        		"null", "empty", "token null", "TOKEN NULL", "Null", "Empty", "rt", "Token Null", "Refreh Token", "refresh token", "Token", "Null", "EMPTY refresh_token"};
        final List<String> s = Arrays.asList(text);
        final Mac hmac;
        try {
        		hmac = Mac.getInstance("HmacSHA256");
			hmac.init(new SecretKeySpec(macKey.getBytes(), "HmacSHA256"));
			final byte[] mac = Base64.getEncoder().encode(hmac.doFinal(requestString.getBytes()));
			System.out.println("Mac id=\"" + accessToken + "\", ts=\"" + timestamp + "\", nonce=\"" + nonce + "\", mac=\"" + new String(mac) + "\"");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(
					code_verifier.getBytes(StandardCharsets.US_ASCII));
			for (String t: s) {
				System.out.println("text (" + t + ") ---> " + Base64.getEncoder().encode(digest.digest(t.getBytes())));
			}
			final String xmlString = "PHNhbWwyOkFzc2VydGlvbiB4bWxuczpzYW1sMj0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiIgSUQ9IjRkNmJiZjkxLThhN2UtNGRkMi04ZGEyLWFjNWMwZWU0ZWQ5MSIgSXNzdWVJbnN0YW50PSIyMDIwLTA2LTE4VDE4OjQ3OjM0LjQ3NloiIFZlcnNpb249IjIuMCI-PHNhbWwyOklzc3Vlcj5OR0V3WXpJMk5XTXRZakZtTkMwMFpHTTVMVGcwTXprdE5XSmlOVFV4TkRSaU56azI8L3NhbWwyOklzc3Vlcj48ZHM6U2lnbmF0dXJlIHhtbG5zOmRzPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjIj4KPGRzOlNpZ25lZEluZm8-CjxkczpDYW5vbmljYWxpemF0aW9uTWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIi8-CjxkczpTaWduYXR1cmVNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNyc2Etc2hhMjU2Ii8-CjxkczpSZWZlcmVuY2UgVVJJPSIjNGQ2YmJmOTEtOGE3ZS00ZGQyLThkYTItYWM1YzBlZTRlZDkxIj4KPGRzOlRyYW5zZm9ybXM-CjxkczpUcmFuc2Zvcm0gQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjZW52ZWxvcGVkLXNpZ25hdHVyZSIvPgo8ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIj48ZWM6SW5jbHVzaXZlTmFtZXNwYWNlcyB4bWxuczplYz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIiBQcmVmaXhMaXN0PSJkcyBzYW1sMiIvPjwvZHM6VHJhbnNmb3JtPgo8L2RzOlRyYW5zZm9ybXM-CjxkczpEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjc2hhMSIvPgo8ZHM6RGlnZXN0VmFsdWU-aUl1MmZzVDljd0xwSkFvYVBqbVhzUmpBeVk4PTwvZHM6RGlnZXN0VmFsdWU-CjwvZHM6UmVmZXJlbmNlPgo8L2RzOlNpZ25lZEluZm8-CjxkczpTaWduYXR1cmVWYWx1ZT4KYnk4Rmd6THM2dTNXdU90aG9lM0RlMzJHUkF2SUpGdkQ0OWRtWUVJQndaR01Qb25OSDJ1NlhZSkJqQ1F6NjZZd0dCOEpGSFNrN0MzRgprbnZmSXkxbkFtdHZjc094NnpJRlJ0cUVhKzN5djYxSTM4ZFFLU0lQUitJL0RZdWgwL1FaN1cyOWJrdEtYWWFVOW82dFMxdXBqTTJuCkk2TGFOZzMzeWE2TWh2cFNiVkxlZVN2MlRBZW5xVmk2aXNMdGRhK1RyMTdIa2xqeXBQZkw5MTdsTVhDMU1aTjRNaVBLS253VjM5TEEKamY0Z2lXbFh2TCtYMjl6aHZJRmR0a3hQQUZZdllXUWVPVVlDK2xMSE9tMnBmaVJpWmxKQ3lnVGNyV25uWms1YXgzM0d6dElocTZtLwpaL0pPRlhZbE5sc3ZjMnJqWmRFZlNQS2NpOXpESWtWcEJrbGVDdz09CjwvZHM6U2lnbmF0dXJlVmFsdWU-CjxkczpLZXlJbmZvPjxkczpYNTA5RGF0YT48ZHM6WDUwOUNlcnRpZmljYXRlPk1JSURVRENDQWppZ0F3SUJBZ0lHQVhMRk0wVmFNQTBHQ1NxR1NJYjNEUUVCQ3dVQU1Ha3hGakFVQmdOVkJBTU1EU291ZDI5eWEyUmhlUzVqYjIweEVEQU9CZ05WQkFvTUIxZHZjbXRrWVhreERqQU1CZ05WQkFzTUJYTjFjR1Z5TVJNd0VRWURWUVFIREFwUWJHVmhjMkZ1ZEc5dU1Rc3dDUVlEVlFRSURBSkRRVEVMTUFrR0ExVUVCaE1DVlZNd0hoY05NakF3TmpFM01EY3dNREF3V2hjTk1qTXdOakUzTURjd01EQXdXakJwTVJZd0ZBWURWUVFEREEwcUxuZHZjbXRrWVhrdVkyOXRNUkF3RGdZRFZRUUtEQWRYYjNKclpHRjVNUTR3REFZRFZRUUxEQVZ6ZFhCbGNqRVRNQkVHQTFVRUJ3d0tVR3hsWVhOaGJuUnZiakVMTUFrR0ExVUVDQXdDUTBFeEN6QUpCZ05WQkFZVEFsVlRNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQW01ajg3ZDhTU2JKcnFyU3lUMFJzWmJRZDFTTTMzZ2FHS3A5NGF4aDhzNHpvVXlBMWtsWkZHc2w2NGF4bWMveVMycXJRai9yRFU5Q1JGS0E1UmhZZkNlejhxV3lJVDA1Szd3ZDdFKzFkdVJxZXgxWEFXZmZMbU1xOWJPR08yeWtTYkhFRmxQY0RKQjV3NGNpTU1lRmVNN1FNM1dmRVd3YTJvRGxoalNVU1AzUjVmNW9POHh1NUErd2w0SmoxYllJNHBFWFd5RzBqOHE2TWRLWWJGZVhjNWJQTWc4MnErYWlGUytxOEJQVG9NTS9jOWRMdVBwbjhFRGFHRU94Tm4xSllKbCtKUHEvU0p6VmVqRGdDL28vc1d2VDVIQTE3OFFiWllpdFd6WElsZ25KUHllaFlLUXdYV25EVVpSM2d3MGhTblk5MVVUTWpPZ2FOS1UycTBGcmlXd0lEQVFBQk1BMEdDU3FHU0liM0RRRUJDd1VBQTRJQkFRQ1lZajdzQXB2blBPSXh4Wk9Ccnk1V1AxRGU0Z2JScjNCZktkQXo4VG5qQU8reXp3MkVEN3lRWkU4djhhamRnWjNGRHlCcktvOGtwcTBDWnZuS2FVVk1hV0pXbWlNN0VYM3VPQktNQzk0RE16MWRFZjVoTHFaZ3h3dERlbmh2Qk9uQ092bnRIc24vVXN4OUcxUkowcXJuazkrV1A1UHhKeXRuSmhzTTR6L3FKMWZ4czBsSXQ3d0RHUGVkNjhzL3RnQmNKTTg3VEJRZnd5WG1veTNoMkdYN2ROK2gyays2TGNLOEg5eTA4WVo0d2haL2pFVVpPQUhVeTZNY3Q2MjdHSEhUYUU1aDZNZkZyL1lXMHBXZkFueC9UVG5mbTF0UmxHU0tlQUg1aytSWFF3Q1k5YTJKeWZyR0xLY1NsbFpDVUF6UC9UN2ZONXNyN0xpVm5IL1lzTlRQPC9kczpYNTA5Q2VydGlmaWNhdGU-PC9kczpYNTA5RGF0YT48L2RzOktleUluZm8-PC9kczpTaWduYXR1cmU-PHNhbWwyOlN1YmplY3Q-PHNhbWwyOk5hbWVJRD5sbWNuZWlsPC9zYW1sMjpOYW1lSUQ-PHNhbWwyOlN1YmplY3RDb25maXJtYXRpb24gTWV0aG9kPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6Y206YmVhcmVyIj48c2FtbDI6U3ViamVjdENvbmZpcm1hdGlvbkRhdGEgTm90T25PckFmdGVyPSIyMDIwLTA2LTIwVDA3OjAwOjAwLjAwMFoiIFJlY2lwaWVudD0iaHR0cDovL3d3dy53b3JrZGF5LmNvbSIvPjwvc2FtbDI6U3ViamVjdENvbmZpcm1hdGlvbj48L3NhbWwyOlN1YmplY3Q-PHNhbWwyOkNvbmRpdGlvbnMgTm90QmVmb3JlPSIyMDIwLTA2LTE3VDA3OjAwOjAwLjAwMFoiIE5vdE9uT3JBZnRlcj0iMjAyMC0wNi0yMFQwNzowMDowMC4wMDBaIj48c2FtbDI6QXVkaWVuY2VSZXN0cmljdGlvbj48c2FtbDI6QXVkaWVuY2U-aHR0cDovL3d3dy53b3JrZGF5LmNvbTwvc2FtbDI6QXVkaWVuY2U-PC9zYW1sMjpBdWRpZW5jZVJlc3RyaWN0aW9uPjwvc2FtbDI6Q29uZGl0aW9ucz48c2FtbDI6QXV0aG5TdGF0ZW1lbnQgQXV0aG5JbnN0YW50PSIyMDIwLTA2LTE4VDE4OjQ3OjM0LjQ3NloiPjxzYW1sMjpBdXRobkNvbnRleHQ-PHNhbWwyOkF1dGhuQ29udGV4dENsYXNzUmVmPnVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphYzpjbGFzc2VzOlg1MDk8L3NhbWwyOkF1dGhuQ29udGV4dENsYXNzUmVmPjwvc2FtbDI6QXV0aG5Db250ZXh0Pjwvc2FtbDI6QXV0aG5TdGF0ZW1lbnQ-PC9zYW1sMjpBc3NlcnRpb24-";
			System.out.println("base64 url decoded -> " + new String(java.util.Base64.getUrlDecoder().decode(xmlString)));
			System.out.println("url encoded assertiomn: " + java.util.Base64.getUrlEncoder().encodeToString(xml.getBytes()));
			System.out.println("code challenge: " + Base64.getEncoder().encode(encodedhash) + "\nURLEncoded: " + Base64.getUrlEncoder().withoutPadding().encodeToString(encodedhash));
			
			final Map<RequestOriginator, AtomicInteger> sessionsByAuthenticationType = new HashMap<RequestOriginator, AtomicInteger>();
			sessionsByAuthenticationType.putIfAbsent(RequestOriginator.INTERNAL, new AtomicInteger(0));
			sessionsByAuthenticationType.putIfAbsent(RequestOriginator.UI, new AtomicInteger(0));
			sessionsByAuthenticationType.get(RequestOriginator.UI).incrementAndGet();
			sessionsByAuthenticationType.get(RequestOriginator.INTERNAL).getAndIncrement();
			sessionsByAuthenticationType.get(RequestOriginator.INTERNAL).getAndIncrement();
			sessionsByAuthenticationType.get(RequestOriginator.UI).incrementAndGet();
			sessionsByAuthenticationType.computeIfAbsent(RequestOriginator.UI, k -> new AtomicInteger(0));
			final String message = String.format("%s Found %d sessionids for user and %d invalid sessions, Sessions established by trust %d, " +
                    "sessions for other authentication types: %s", "large-sees", 52432, 579472, 893411, sessionsByAuthenticationType);
			final String message1 = MessageFormat.format("{0} Found {1} sessionids for user and {2} invalid sessions, Sessions established by trust {3}, " +
                    "sessions for other authentication types: {4}", "large-sees", 52432, 579472, 893411, sessionsByAuthenticationType);
			System.out.println(message);
			long time = System.currentTimeMillis();
			for (int i = 0; i <= 10000; i++) {
				sessionsByAuthenticationType.putIfAbsent(RequestOriginator.INTERNAL, new AtomicInteger(0));
				sessionsByAuthenticationType.get(RequestOriginator.INTERNAL).incrementAndGet();
			}
			System.out.println("time elapsed -> " + (System.currentTimeMillis() - time));
			time = System.currentTimeMillis();
			for (int i = 0; i <= 10000; i++) {
				sessionsByAuthenticationType.computeIfAbsent(RequestOriginator.UI, k -> new AtomicInteger(0));
				sessionsByAuthenticationType.get(RequestOriginator.UI).incrementAndGet();
			}
			System.out.println("time elapsed -> " + (System.currentTimeMillis() - time));
			System.out.println(sessionsByAuthenticationType);
			
			final Date currDate = new Date();
			System.out.println("currentDate: " + currDate + "          elapsedDate: " + new Date(currDate.getTime() + (120*60000)));
			System.out.println("-->" + Arrays.toString(text));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}

}

enum RequestOriginator {
    INTERNAL("Internal", "InternalRequestHandler", false, false, true),
    UI("UI", "UserInterfaceRequestHandler", false, true, false),
    WEB_SERVICES("Web Services", "WebServiceRequestHandler", true, true, true),
    VPS("VPS", "VPSRequestHandler", true, true, true);

    private final String name;
    private final String nameForLog;
    private final boolean forWebService;
    private final boolean governRequests;
    private final boolean allowWebServiceAuth;

    RequestOriginator(final String name,
                      final String nameForLog,
                      final boolean forWebService,
                      final boolean governRequests,
                      final boolean allowWebServiceAuth) {

        this.name = name;
        this.nameForLog = nameForLog;
        this.forWebService = forWebService;
        this.governRequests = governRequests;
        this.allowWebServiceAuth = allowWebServiceAuth;
    }

    public String getName() {
        return this.name;
    }

    public String getNameForLog() {
        return this.nameForLog;
    }

    public boolean isForWebService() {
        return this.forWebService;
    }

    public boolean isRequestGoverned() {
        return this.governRequests;
    }

    public static RequestOriginator fromName(final String name) {
        if (name != null) {
            for (final RequestOriginator originator : RequestOriginator.values()) {
                if (originator.getName().equals(name)) {
                    return originator;
                }
            }
        }
        return null;
    }

    public static RequestOriginator getDefaultOriginator() {
        return WEB_SERVICES;
    }
    
    public boolean isAllowWebServiceAuth() {
        return this.allowWebServiceAuth;
    }
}
