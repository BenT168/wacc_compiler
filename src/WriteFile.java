import java.io.*;


public class WriteFile {

    public BufferedWriter writer = null;

    public void writeToFile(String argfile) throws IOException {

        //Get file name without extension
        String temp = getFileName(argfile);
        String writeFile = temp.trim();


        //Write to file.s
        File wFile = new File(writeFile);
        FileOutputStream fos = new FileOutputStream(wFile);
        OutputStreamWriter out = new OutputStreamWriter(fos, "utf-8");
        writer = new BufferedWriter(out);
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
