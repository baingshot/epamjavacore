package ru.epam.javacore.lesson_12_io_nio.lesson.a_1_io;


import ru.epam.javacore.lesson_12_io_nio.lesson.a_0_common.FileService;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileIoService implements FileService {
    private static final String FILE_PATH = "C:\\Users\\student\\Desktop\\dir\\1.txt";
    private static final String DIR_PATH = "C:\\Users\\student\\Desktop";


    @Override
    public void demoCreateFile() {
        File file = new File(FILE_PATH);
        System.out.println("Exists " + file.exists());
        System.out.println("Dir " + file.isDirectory());

    }

    @Override
    public void demoListFiles() {
        File dir = new File(DIR_PATH);
        File[] files = dir.listFiles();
        String[] filesStr = dir.list();

        for (File file1 : files) {
            System.out.println(file1.getName() + " " + file1.isDirectory());
        }
    }

    @Override
    public void demoReadFile() {
        //readFile1();
        // readFileWithChunk2();
        readFileAsClob();
    }

    private void readFile1() {
        File file = new File(FILE_PATH);

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            int i;
            StringBuilder stringBuilder = new StringBuilder();

            while ((i = inputStream.read()) != -1) {
                stringBuilder.append((char) i);
            }
            System.out.println(stringBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
// abcd1234
//size buf = 4 => [abcd], [1234]
//size buf = 3 => [abc], [d12], [34 ?]
//                  3      3      2

    private void readFileWithChunk2() {
        File file = new File(FILE_PATH);
        byte[] chunk = new byte[4096];

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            StringBuilder stringBuilder = new StringBuilder();
            int readBytes = 0;
            while ((readBytes = inputStream.read(chunk, 0, chunk.length - 1)) != -1) {
                System.out.println("RRRRRRRRRR " + readBytes);
                for (int i = 0; i < readBytes; i++) {
                    stringBuilder.append((char) chunk[i]);
                }
            }
            System.out.println(stringBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readFileWithChunk3() {
        File file = new File(FILE_PATH);
        byte[] chunk = new byte[4096];

        try (InputStream inputStream = new FileInputStream(file);) {
            StringBuilder stringBuilder = new StringBuilder();
            int readBytes = 0;
            while ((readBytes = inputStream.read(chunk, 0, chunk.length - 1)) != -1) {
                System.out.println("RRRRRRRRRR " + readBytes);
                for (int i = 0; i < readBytes; i++) {
                    stringBuilder.append((char) chunk[i]);
                }
            }
            System.out.println(stringBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readFileAsClob() {
        File file = new File(FILE_PATH);
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file)
        )) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            System.out.println(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void demoWriterFile() {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(new File("C:\\Users\\student\\Desktop\\dir\\555.txt"))
        )) {

            List<String> data = Arrays.asList("one", "two", "three");
            for (String s : data) {
                writer.write(s + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void demoWriteBinaryFile() {
        String src = "C:\\Users\\student\\Desktop\\dir\\dir.rar";
        String dest = "C:\\Users\\student\\Desktop\\dir\\dir_copy.tt55";

        try (DataInputStream in = new DataInputStream(
                new FileInputStream(src));
             DataOutputStream out = new DataOutputStream(
                     new FileOutputStream(dest))
        ) {
            int read = -1;
            while ((read = in.read())!=-1){
                out.write(read);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
