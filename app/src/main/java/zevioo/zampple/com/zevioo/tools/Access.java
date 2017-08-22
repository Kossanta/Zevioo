package zevioo.zampple.com.zevioo.tools;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * Created by kgiannoulis on 20/4/2017
 */

public class Access {
    AsyncTask checkAccess;
    AccessResponse mResult;

    public Access(AccessResponse temp) {
        this.mResult = temp;
        checkAccess = new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Socket sock = new Socket();
                    SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

                    sock.connect(sockaddr, 1500);
                    sock.close();
                    Utils.INTERNET_ACCESS = true;
                } catch (IOException e) {
                    Utils.INTERNET_ACCESS = false;
                }
                if (mResult!=null){
                    mResult.onSuccess();
                }
                return null;
            }

        };
        checkAccess.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}