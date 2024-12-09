package org.example;

import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.Scanner;

public class FolderScanner {
    private static volatile boolean running = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the folder path to scan: ");
        String path = scanner.nextLine();

        Thread progressThread = new Thread(() -> {
            try {
                while (running) {
                    System.out.print("\rScanning... ");
                    Thread.sleep(500);
                    System.out.print("\rScanning.. ");
                    Thread.sleep(500);
                    System.out.print("\rScanning. ");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        progressThread.start();

        ForkJoinPool pool = new ForkJoinPool();
        FolderTask task = new FolderTask(new File(path));
        try {
            Statistics stats = pool.invoke(task);
            running = false; // Stop the progress indicator
            progressThread.interrupt(); // Stop the progress thread
            progressThread.join(); // Wait for the thread to finish

            System.out.println("\nScanning complete!");
            System.out.println("File count: " + stats.fileCount);
            System.out.println("Folder count: " + stats.folderCount);
            System.out.println("Total size: " + stats.totalSize + " bytes");

        } catch (Exception e) {
            System.out.println("Error scanning folder: " + e.getMessage());
        }
    }

    static class FolderTask extends RecursiveTask<Statistics> {
        private final File folder;

        FolderTask(File folder) {
            this.folder = folder;
        }

        @Override
        protected Statistics compute() {
            Statistics stats = new Statistics();
            File[] files = folder.listFiles();
            if (files == null) return stats;

            for (File file : files) {
                if (file.isDirectory()) {
                    stats.folderCount++;
                    FolderTask subTask = new FolderTask(file);
                    stats.combine(subTask.fork().join());
                } else {
                    stats.fileCount++;
                    stats.totalSize += file.length();
                }
            }
            return stats;
        }
    }

    static class Statistics {
        int fileCount = 0;
        int folderCount = 0;
        long totalSize = 0;

        void combine(Statistics other) {
            this.fileCount += other.fileCount;
            this.folderCount += other.folderCount;
            this.totalSize += other.totalSize;
        }
    }
}