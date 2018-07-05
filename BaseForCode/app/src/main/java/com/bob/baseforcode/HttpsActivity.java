package com.bob.baseforcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by bob
 * on 2018/7/3.
 */
public class HttpsActivity extends AppCompatActivity {

    private static final String TAG = "HttpsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(this::request).start();

    }

    private void request() {

//        String address = "https://www.baidu.com";
        String address = "https://emp.cnpc/ZemmWeb/Frames/Default/Login.aspx";

        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            // From https://www.washington.edu/itconnect/security/ca/load-der.crt
            BufferedInputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));

            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
            } finally {
                caInput.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);



            URL url = new URL(address);
            Log.d(TAG, "request: " + address);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(context.getSocketFactory());
            InputStream is = connection.getInputStream();

            InputStreamReader reader = new InputStreamReader(is);

            BufferedReader br = new BufferedReader(reader);

            StringBuilder buffer = new StringBuilder();

            String s;
            while ((s = br.readLine()) != null) {
                buffer.append(s);
            }
            Log.d(TAG, "result: " + buffer.toString());

        } catch (IOException
                | CertificateException
                | KeyManagementException
                | NoSuchAlgorithmException
                | KeyStoreException e) {
            e.printStackTrace();
        }

    }
}
