package model;

import java.util.List;

public class AuditStats {
	
	private int numCases = 0;
	private int numCases045 = 0;
	private int numBiopsies = 0;
	private int numCatFour = 0;
	private int numCatFive = 0;
	private int numBiopsiesMalignant = 0;
	private int numCatFourMalignant = 0;
	private int numCatFiveMalignant = 0;
	private int numBiopsiesBenign = 0;
	private int numLost45 = 0;
	private int numDuctalCarcinoma = 0;
	private int numInvDuctalLobular = 0;
	private int numInvDuctalLobularAxillary = 0;
	private int numTruePositive = 0;
	private int numFalsePositive = 0;
	private double posPredFourFive = 0;
	private double posPredFour = 0;
	private double posPrdFive = 0;
	private int cancerDetectionRate = 0;
	private int recallRate = 0;
	
	private int[] counts = {numCases, numCatFour, numCatFive};
	private String[] descriptions;
	
	public int getNumCases() {
		return numCases;
	}

	public void setNumCases(int numCases) {
		this.numCases = numCases;
	}

	public int getNumCases045() {
		return numCases045;
	}

	public void setNumCases045(int numCases045) {
		this.numCases045 = numCases045;
	}

	public int getNumBiopsies() {
		return numBiopsies;
	}

	public void setNumBiopsies(int numBiopsies) {
		this.numBiopsies = numBiopsies;
	}

	public int getNumCatFour() {
		return numCatFour;
	}

	public void setNumCatFour(int numCatFour) {
		this.numCatFour = numCatFour;
	}

	public int getNumCatFive() {
		return numCatFive;
	}

	public void setNumCatFive(int numCatFive) {
		this.numCatFive = numCatFive;
	}

	public int getNumBiopsiesMalignant() {
		return numBiopsiesMalignant;
	}

	public void setNumBiopsiesMalignant(int numBiopsiesMalignant) {
		this.numBiopsiesMalignant = numBiopsiesMalignant;
	}

	public int getNumCatFourMalignant() {
		return numCatFourMalignant;
	}

	public void setNumCatFourMalignant(int numCatFourMalignant) {
		this.numCatFourMalignant = numCatFourMalignant;
	}

	public int getNumCatFiveMalignant() {
		return numCatFiveMalignant;
	}

	public void setNumCatFiveMalignant(int numCatFiveMalignant) {
		this.numCatFiveMalignant = numCatFiveMalignant;
	}

	public int getNumBiopsiesBenign() {
		return numBiopsiesBenign;
	}

	public void setNumBiopsiesBenign(int numBiopsiesBenign) {
		this.numBiopsiesBenign = numBiopsiesBenign;
	}

	public int getNumLost45() {
		return numLost45;
	}

	public void setNumLost45(int numLost45) {
		this.numLost45 = numLost45;
	}

	public int getNumDuctalCarcinoma() {
		return numDuctalCarcinoma;
	}

	public void setNumDuctalCarcinoma(int numDuctalCarcinoma) {
		this.numDuctalCarcinoma = numDuctalCarcinoma;
	}

	public int getNumInvDuctalLobular() {
		return numInvDuctalLobular;
	}

	public void setNumInvDuctalLobular(int numInvDuctalLobular) {
		this.numInvDuctalLobular = numInvDuctalLobular;
	}

	public int getNumInvDuctalLobularAxillary() {
		return numInvDuctalLobularAxillary;
	}

	public void setNumInvDuctalLobularAxillary(int numInvDuctalLobularAxillary) {
		this.numInvDuctalLobularAxillary = numInvDuctalLobularAxillary;
	}

	public int getNumTruePositive() {
		return numTruePositive;
	}

	public void setNumTruePositive(int numTruePositive) {
		this.numTruePositive = numTruePositive;
	}

	public int getNumFalsePositive() {
		return numFalsePositive;
	}

	public void setNumFalsePositive(int numFalsePositive) {
		this.numFalsePositive = numFalsePositive;
	}

	public double getPosPredFourFive() {
		return posPredFourFive;
	}

	public void setPosPredFourFive(double posPredFourFive) {
		this.posPredFourFive = posPredFourFive;
	}

	public double getPosPredFour() {
		return posPredFour;
	}

	public void setPosPredFour(double posPredFour) {
		this.posPredFour = posPredFour;
	}

	public double getPosPrdFive() {
		return posPrdFive;
	}

	public void setPosPrdFive(double posPrdFive) {
		this.posPrdFive = posPrdFive;
	}

	public int getCancerDetectionRate() {
		return cancerDetectionRate;
	}

	public void setCancerDetectionRate(int cancerDetectionRate) {
		this.cancerDetectionRate = cancerDetectionRate;
	}

	public int getRecallRate() {
		return recallRate;
	}

	public void setRecallRate(int recallRate) {
		this.recallRate = recallRate;
	}

	public int[] getCounts() {
		return counts;
	}

	public void setCounts(int[] counts) {
		this.counts = counts;
	}

	public String[] getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String[] descriptions) {
		this.descriptions = descriptions;
	}

	public void calculateStats(List<Patient> patients){
		
		numCases = patients.size();
		getCounts()[0] = numCases;
		
		for(Patient patient : patients){
			if(patient.getScreening_category() == 4 || patient.getDiagnostic_category() == 4){
				numCatFour++;
				getCounts()[1] = numCatFour;
			}
			if(patient.getScreening_category() == 5 || patient.getDiagnostic_category() == 5){
				numCatFive++;
				getCounts()[2] = numCatFive;
			}
		}
		
	}
	
	private void loadStats(){
		
	}

}
