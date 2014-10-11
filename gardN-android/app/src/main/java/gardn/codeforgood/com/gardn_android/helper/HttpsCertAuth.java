package gardn.codeforgood.com.gardn_android.helper;

import android.content.Context;

import com.loopj.android.http.MySSLSocketFactory;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManagerFactory;

import gardn.codeforgood.com.gardn_android.exception.MyRuntimeException;


public class HttpsCertAuth {

    private static HttpsCertAuth ourInstance = new HttpsCertAuth();
    private KeyStore keyStore;

    private HttpsCertAuth(){
    }

    public static HttpsCertAuth getInstance() {
        return ourInstance;
    }

    private Certificate loadCAFromInputStream(InputStream caInput){
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            throw new MyRuntimeException(e);
        }

        Certificate ca;
        try {
            ca = cf.generateCertificate(caInput);
            System.out.println("ca="+((X509Certificate) ca).getSubjectDN());
        } catch (CertificateException e) {
            throw new MyRuntimeException(e);
        } finally {
            try {
                caInput.close();
            } catch (IOException e) {
                throw new MyRuntimeException(e);
            }
        }
        return ca;
    }


    private KeyStore createKeystore(Certificate ca){
        // Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance(keyStoreType);
        } catch (KeyStoreException e) {
            throw new MyRuntimeException(e);
        }
        try {
            keyStore.load(null, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        try {
            keyStore.setCertificateEntry("ca", ca);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return keyStore;

    }

    private TrustManagerFactory getTrustManagerFactory(){
        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = null;
        try {
            tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new MyRuntimeException(e);
        }
        return tmf;
    }

    public KeyStore initKeyStore(Context context, String fileName){

        InputStream caInput = null;
        try {
            caInput = new BufferedInputStream(context.getAssets().open(fileName));
        } catch (IOException e) {
            throw new MyRuntimeException(e);
        }
        Certificate  ca = loadCAFromInputStream(caInput);
        keyStore = createKeystore(ca);

        return keyStore;
    }

    public DefaultHttpClient getHttpsClient(){
        try {
            SSLSocketFactory sf = new MySSLSocketFactory(keyStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("https",sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }
}
