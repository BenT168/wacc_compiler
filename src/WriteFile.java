import java.io.*;


public class WriteFile {

    public BufferedWriter writer = null;

    public String writeToFile(File file) throws IOException {

        String argfile = file.getName();
        //Get file name without extension
        String temp = getFileName(argfile);
        String writeFile = temp.trim();
        return writeFile;
    }


    /*Get .s file name e.g transforms foo.wacc -> foo.s */
    public String getFileName(String file) {
        char[] temp = new char[file.length()];

        int i = 0;
        while(file.charAt(i) != '.') {
            temp[i] = file.charAt(i);
            i++;
        }
        //concatenate .s at end of file name
        temp[i] = '.';
        temp[i+1] = 's';

        return String.valueOf(temp);
    }
}
