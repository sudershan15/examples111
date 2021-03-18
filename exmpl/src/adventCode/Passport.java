package adventCode;

public class Passport {
	
	private static final String regexForPid = "\\d{9}"; //9 digits
	private static final String regexForHcl = "^#[a-f_0-9]{6}"; //# followed by 0-9 or a-f exactly 6 chars
	private static final String regexForHgt1 = "(59|6[0-9]|7[0-6])in$"; //59-76 in
	private static final String regexForHgt2 = "(1[5-8][0-9]|19[0-3])cm$"; //150-193cm
	
	int byr;
	int iyr;
	int eyr;
	String hgt;
	String hcl;
	String ecl;
	String pid;
	long cid;
	
	public Passport() {
		
	}
	
	public int getByr() {
		return byr;
	}
	public void setByr(int byr) {
		this.byr = byr;
	}
	public int getIyr() {
		return iyr;
	}
	public void setIyr(int iyr) {
		this.iyr = iyr;
	}
	public int getEyr() {
		return eyr;
	}
	public void setEyr(int eyr) {
		this.eyr = eyr;
	}
	public String getHgt() {
		return hgt;
	}
	public void setHgt(String hgt) {
		this.hgt = hgt;
	}
	public String getHcl() {
		return hcl;
	}
	public void setHcl(String hcl) {
		this.hcl = hcl;
	}
	public String getEcl() {
		return ecl;
	}
	public void setEcl(String ecl) {
		this.ecl = ecl;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
//		try {
//			this.pid = Long.parseLong(pid);
//		} catch(Exception e) {
//			System.out.println("Incorrect pid " + pid);
//			this.pid = 0L;
//		}
		this.pid = pid;
	}
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	
	public boolean isValid() {
		if (this.byr < 1920 || this.byr > 2002 || this.iyr < 2010 || this.iyr > 2020) {
			return false;
		}
		if (this.eyr > 2030 || this.eyr < 2020) {
			return false;
		}
		if (this.hgt == null || this.hgt == "" || !(this.hgt.matches(regexForHgt1) || this.hgt.matches(regexForHgt2))) {
			return false;
		}
		if (this.hcl == null || this.hcl == "" || !this.hcl.matches(regexForHcl)) {
			return false;
		}
		if (this.pid == null || this.pid == "" || !this.pid.matches(regexForPid)) {
			return false;
		}
		if (EyeColor.fromName(this.ecl) == null) {
			return false;
		}
		return true;
	}

	enum EyeColor {
		brn, blu, amb, gry, grn, hzl, oth;
		
		static EyeColor fromName(String ecl) {
			try {
				return EyeColor.valueOf(ecl);
			} catch (Exception e) {
				return null;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Passport [byr=" + byr + ", iyr=" + iyr + ", eyr=" + eyr + ", hgt=" + hgt + ", hcl=" + hcl + ", ecl="
				+ ecl + ", pid=" + pid + ", cid=" + cid + ", isValid= " + this.isValid() + "]";
	}
}
