import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class OMEventClient {
    public static void main(String[] args){
        try{
            Socket client = new Socket("localhost", 8081);
            OutputStream outputStream = client.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream), 1024);
            String res = "HTTP/1.1 200 OK\n" +
                    "Date: Mon, 26 Nov 2018 14:40:14 GMT\n" +
                    "Content-Type: text/html; charset=utf-8\n" +
                    "Transfer-Encoding: chunked\n" +
                    "Connection: keep-alive\n" +
                    "gear: 1\n" +
                    "vikingrCache: 60000\n" +
                    "Vikingr-Cache-TTL: 27374\n" +
                    "Vary: Origin,Accept-Encoding\n" +
                    "Content-Encoding: gzip\n" +
                    "Expires: Mon, 26 Nov 2018 14:40:44 GMT\n" +
                    "Cache-Control: max-age=30\n" +
                    "X-Cache: BYPASS from ks-sh2-webcdn-14.hdslb.com\n" +
                    "\n" +
                    "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                    "<Transfer attribute=\"Queue\">\n" +
                    "  <visitor id=\"1\"/>\n" +
                    "    <ext id=\"200\"/>\n" +
                    "</Transfer>";
            bufferedWriter.write(res);
            bufferedWriter.flush();
            bufferedWriter.close();
            client.close();

        }catch (Exception e){

        }

    }
}
