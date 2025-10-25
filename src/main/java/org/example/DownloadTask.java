package org.example;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class DownloadTask implements Task {
    private final String url;
    private final String targetPath;
    private volatile boolean running = false;
    private Thread worker;

    public DownloadTask(String url, String targetPath) {
        this.url = url;
        this.targetPath = targetPath;
    }

    @Override
    public void start() {
        if (running) return;
        running = true;
        worker = new Thread(() -> {
            try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                 FileOutputStream out = new FileOutputStream(targetPath)) {

                byte[] buf = new byte[8192];
                int n;
                while (running && (n = in.read(buf)) != -1) {
                    out.write(buf, 0, n);
                }
                out.flush();

                if (!running) {
                    try { new java.io.File(targetPath).delete(); } catch (Exception ignored) {}
                    System.out.println("Скачивание остановлено, файл удалён.");
                } else {
                    System.out.println("Скачивание завершено: " + targetPath);
                }
            } catch (IOException e) {
                System.out.println("Ошибка скачивания: " + e.getMessage());
            } finally {
                running = false;
            }
        }, "download-worker");
        worker.start();
    }

    @Override
    public void stop() {
        running = false;
        if (worker != null) {
            try { worker.join(); }
            catch (InterruptedException ignored) {}
        }
    }

    // ДЕМО
    public static void main(String[] args) throws InterruptedException {
        Task t = new DownloadTask("https://httpbin.org/bytes/1048576", "download.bin");
        t.start();
        Thread.sleep(2000);
        t.stop();
    }
}
