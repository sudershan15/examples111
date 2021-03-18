package certificate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class CertTest {

	public static RSAPublicKey getPublicKeyFromCertificate(String certificate) throws CertificateException {
        certificate = certificate.replaceAll("-----.* CERTIFICATE-----", "").replaceAll("\\n", "");
        System.out.println("\n\n"+ certificate);
        InputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(certificate));
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        X509Certificate x509Certificate = (X509Certificate) certFactory.generateCertificate(inputStream);
        final RSAPublicKey publicKey = (RSAPublicKey) x509Certificate.getPublicKey();
        System.out.println(publicKey);
        return publicKey;
    }
	
	public static void main(String[] args) {
		String cert = "-----BEGIN CERTIFICATE-----\n" +
                "MIIDUDCCAjigAwIBAgIGAXa6M9/ZMA0GCSqGSIb3DQEBCwUAMGkxFjAUBgNVBAMM\n" +
                "DSoud29ya2RheS5jb20xEDAOBgNVBAoMB1dvcmtkYXkxDjAMBgNVBAsMBXN1cGVy\n" +
                "MRMwEQYDVQQHDApQbGVhc2FudG9uMQswCQYDVQQIDAJDQTELMAkGA1UEBhMCVVMw\n" +
                "HhcNMjAxMjMxMDgwMDAwWhcNMjUxMjMxMDgwMDAwWjBpMRYwFAYDVQQDDA0qLndv\n" +
                "cmtkYXkuY29tMRAwDgYDVQQKDAdXb3JrZGF5MQ4wDAYDVQQLDAVzdXBlcjETMBEG\n" +
                "A1UEBwwKUGxlYXNhbnRvbjELMAkGA1UECAwCQ0ExCzAJBgNVBAYTAlVTMIIBIjAN\n" +
                "BgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApmSLt53iamkQ3mwxW+uRmfj8SYba\n" +
                "51Y1MJVvudxwjAIGcuDTTvrituCemuV0Z2NVQC6Tmky3YIZM4oKzSsgVF75C5voB\n" +
                "whYrq2b12BIxE298FiuYFVb5nL6biXX0TM3u8V5BsZOxAsjaePrqgCFwDb/Uek0O\n" +
                "056rmS3v0XtThwf2dtIhQXzwGkms6IXuSxLJ8e2E+9hqAwreoo8tWS5pR2bRJN+e\n" +
                "uVXUn2GAbUbZmeowhM7emhqPjH+7NAYZe6oX8nPRVXbM4Wah1n86R4SDGjMcAVia\n" +
                "/wa6dB6jhswYGesirx6r2kSBwom7DNQgCI/t/5YPWF+HyDstdPPatWTCUwIDAQAB\n" +
                "MA0GCSqGSIb3DQEBCwUAA4IBAQBl2gnqMFqM1lYLX/azRIS8ueIz0bFy7o1qzSMd\n" +
                "Z5ymhnEwboHkHI7glQx2GRKkBzLjdvZ+9BigSh7lLOasO9JJOtG/pkukqC2SQcBI\n" +
                "z393gXTNL2R1z3dX1cT0BbEbDGKlLID0A9TruCy6ocuvRT6ty1nDBrh5WCfCv1di\n" +
                "TnIHxvmIGSXaEUneK8YSm5E1c7NBwecxdULIVvoh9v/qjpCI5LIg6OWQU27UHGrp\n" +
                "FmTq6rwNyTyP+nm+f/RwRVnpp2bdkHOfV1w5kp/alB+JOol5MilAiT3N6k4Kbd6e\n" +
                "Sjzmtkxknz4tcjkognHKxDUDyV0o5XM4l0K5wvt4YdvDpqAT\n" +
                "-----END CERTIFICATE-----";
		
		try {
			getPublicKeyFromCertificate(cert);
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
