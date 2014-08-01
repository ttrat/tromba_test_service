package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Constants {
	
	/*public static final String[] CATEGORY_DESCRIPTIONS = {"Incomplete -- Need Additional Imaging Evaluation and/or Prior Mammograms for Comparison",
														"Negative",
														"Benign Finding(s)",
														"Probably Benign Finding -- Initial Short-Interval Follow-up",
														"Suspicious Abnormality -- Biopsy Should Be Considered",
														"Highly Suggestive of Malignancy -- Appriopriate Action Should Be Taken",
														"Known Biopsy-Proven Malignancy -- Appropriate Action Should Be Taken"};*/
	
	public static final int NUM_CATEGORIES = 7;
	
	public static Map<Integer, String> IMAGING_CATEGORIES;
	{
		Map<Integer, String> temp = new HashMap<Integer, String>();
		temp.put(-1,"N/A");
		temp.put(0,"Incomplete");
		temp.put(1,"Negative");
		temp.put(2,"Benign Finding(s)");
		temp.put(3,"Probably Benign Finding");
		temp.put(4,"Suspicious Abnormality");
		temp.put(5,"Phyllodes Tumor");
		temp.put(6,"Highly Suggestive of Malignancy");
		temp.put(7,"Known Biopsy-Proven Malignancy");
		IMAGING_CATEGORIES = Collections.unmodifiableMap(temp);
	}
	
	public static Map<Integer, String> BIOPSY_RESULTS;
	{
		Map<Integer, String> temp = new HashMap<Integer, String>();
		temp.put(-1,"N/A");
		temp.put(0,"DCIS");
		temp.put(1,"LCIS");
		temp.put(2,"Invasive Ductal Carcinoma");
		temp.put(3,"Inflammatory Breast Cancer");
		temp.put(4,"Fibroadenoma");
		temp.put(5,"Phyllodes Tumor");
		temp.put(6,"Atypical Hyperplasia");
		temp.put(7,"Microcystic Disease");
		BIOPSY_RESULTS = Collections.unmodifiableMap(temp);
	}
	

}
