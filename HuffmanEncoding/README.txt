Running in IntelliJ IDEA

1. Open IntelliJ IDEA and load the project.

2. Click Run > Edit Configurations.

	Set the following fields:

	Name: your preferred name

	Main class: Main 

	Working Directory: the project's location
 
	Program Arguments: 

	- Use FreqTable to encode ClearText and decode Encoded
	"input/FreqTable.txt" "input/ClearText.txt" "input/Encoded.txt" "Output/Encoded_Output.txt" "Output/Decoded_Output.txt" 
	
	- Use the generated frequency table to encode ClearText and decode encoded
	"input/GeneratedFrequencyTable.txt" "input/ClearText.txt" "input/Encoded.txt" "Output/Encoded_Output_UsingGeneratedFreqTable.txt" "Output/Decoded_Output_UsingGeneratedFreqTable.txt" 

	- Use FreqTable for my extra clear text and encoded
	"input/FreqTable.txt" "input/More_ClearText.txt" "input/More_Encoded.txt" "Output/More_Encoded_Output.txt" "Output/More_Decoded_Output.txt" 

	JDK: e.g. Java 22 SDK

3. Click Apply and OK.

4. Click Run 