import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CurrencyDatabase {

    public static void main(String[] args) {
        // Call the method to process the CSV file
        String[] currencies = currencies();
        
        // Print the ArrayList to verify
        for(int i = 0; i < currencies.length; i++) {
        	System.out.print(currencies[i] + " ");
        }
    }
    
    public static String[] currencyArray() {
		String[] currencies = CurrencyDatabase.currencies();
		return currencies;
	}

    public static String[] currencies() {
        String file = "src/resources/currencies.csv";
        BufferedReader reader = null;
        String line = "";
        ArrayList<String> currencyList = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {

                String[] row = line.split(",");

                if (row.length > 2 && !row[1].equals("Currency Code")) {
                    currencyList.add(row[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arrayListToArray(currencyList);
    }
    
    public static String[] arrayListToArray(ArrayList<String> list) {
    	String[] array = new String[list.size()];
    	for(int i = 0; i < list.size(); i++) {
    		array[i] = list.get(i);
    	}
    	
    	return array;
    }
}