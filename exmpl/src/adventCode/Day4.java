package adventCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {

	public static void main(String[] args) {
		List<Passport> passports = getPassportFromList(getPassportsFromFile());
		System.out.println(passports.size());
		int count = 0;
		for (Passport passport: passports) {
			if (passport.isValid()) {
				++count;
				System.out.println(passport);
			}
		}
		System.out.println(count);
	}

	static List<String> getPassportsFromFile() {
		List<String> passports = new ArrayList<>();
		try {
			File myObj = new File("resources/northpole.txt");
			Scanner myReader = new Scanner(myObj);
			StringBuilder sb = new StringBuilder();

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (data.hashCode() == 0 || data == "" || data == "\t" || data == "\n") {
					passports.add(sb.toString().trim());
					sb.delete(0, sb.length() - 1);
				}
				sb.append(data);
				sb.append(" ");
			}
			passports.add(sb.toString());
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return passports;
	}
	
	static List<Passport> getPassportFromList(final List<String> listOfPassportStrings) {
		List<Passport> passports = new ArrayList<>();
		for (String passportString: listOfPassportStrings) {
			passports.add(retreivePassport(passportString));
		}
		return passports;
	}
	
	
	public static Passport retreivePassport(String passportString) {
		Passport passport = new Passport();
		String[] passportParts = passportString.split(" ");
		for (String passportPart: passportParts) {
			String[] passportProperty = passportPart.split(":");
			switch (passportProperty[0]) {
			case "eyr": passport.setEyr(Integer.parseInt(passportProperty[1])); break;
			case "byr": passport.setByr(Integer.parseInt(passportProperty[1])); break;
			case "iyr": passport.setIyr(Integer.parseInt(passportProperty[1])); break;
			case "hgt": passport.setHgt(passportProperty[1]); break;
			case "hcl": passport.setHcl(passportProperty[1]); break;
			case "ecl": passport.setEcl(passportProperty[1]); break;
			case "pid": passport.setPid(passportProperty[1]); break;
			case "cid": passport.setCid(Long.parseLong(passportProperty[1])); break;
			default:
			}
		}
		return passport;
	}
}
