package ru.greatlarder.technicalassistant.services.manager;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class SaveFileFTPServer {

    private String server;
    private Integer port;
    private String user;
    private String password;
    private FTPClient ftp;

    public void saveFile(User user, String url, File file) throws IOException {
        this.server = user.getServerFTP();
        this.port = user.getPortFTP();
        this.user = user.getUserFTP();
        this.password = user.getPasswordFTP();
    }


    public void open() throws IOException {
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        if (server != null && port != null) {
            ftp.connect(server, port);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                throw new IOException("Exception in connecting to FTP Server");
            }
            ftp.login(user, password);
        }
    }

    public void close() {
        try {
            ftp.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<String> listFiles(String path) throws IOException {
        open();
        FTPFile[] files = ftp.listFiles(path);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
        close();
        return Arrays.stream(files)
                .map(FTPFile::getName)
                .collect(Collectors.toList());
    }

    public void putFileToPath(File file, String path) throws IOException {
        open();
        InputStream inputStream = new FileInputStream(file);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
        boolean done = ftp.storeFile(path + file.getName(), inputStream);
        inputStream.close();
        close();
        if (done) {
            System.out.println("The first file is uploaded successfully.");
        } else {
            System.out.println("File not uploaded.");
        }
    }

    public void downloadFile(String source, String destination) throws IOException {
        open();
        FileOutputStream out = new FileOutputStream(destination);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
        ftp.retrieveFile(source, out);
        out.close();
        close();
    }
}
