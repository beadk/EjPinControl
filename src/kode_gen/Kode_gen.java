package kode_gen;

import java.util.*;

public class Kode_gen {
	Random gen = new Random();
	String kode = "";

	public String kode(String kode, int i, int codeLength) {
		if (i == codeLength) {
			return kode;
		} else {
			if (i == 0) {
				kode = "";
			}
			i++;
			return kode(kode + "" + gen.nextInt(10), i, codeLength);
		}
	}

	public String kodeBogTal(String kode, int i, int codeLength) {
		if (i == codeLength) {
			if (!isTrue(kode)) {
				return kodeBogTal("", 0, codeLength);
			} else {
				return kode;
			}
		} else {
			if (i == 0) {
				kode = "";
			}
			i++;
			String next = "";
			String codePOS = "abcdefghijklmnopqrstuvwxyz0123456789";
			String[] codeA = codePOS.split("");
			next = codeA[gen.nextInt(codeA.length)];
			return kodeBogTal(kode + "" + next, i, codeLength);
		}
	}

	public String kodeSSBogTal(String kode, int i, int codeLength) {
		if (i == codeLength) {
			if (!isTrueSS(kode)) {
				return kodeSSBogTal("", 0, codeLength);
			} else {
				return kode;
			}
		} else {
			if (i == 0) {
				kode = "";
			}
			i++;
			String next = "";
			String codePOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			String[] codeA = codePOS.split("");
			next = codeA[gen.nextInt(codeA.length)];
			return kodeSSBogTal(kode + "" + next, i, codeLength);
		}
	}

	public String mockKode(String kode, int i, int codeLength) {
		if (i == codeLength) {
			return kode;
		} else {
			if (i == 0) {
				kode = "";
			}
			i++;
			return mockKode(kode + "" + 0, i, codeLength);
		}
	}

	public boolean isTrueSS(String code) {
		try {
			String n = ".*[0-9].*";
			String a = ".*[a-z].*";
			String A = ".*[A-Z].*";
			return code.matches(n) && code.matches(a) && code.matches(A);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	public boolean isTrue(String code) {
		try {
			String n = ".*[0-9].*";
			String a = ".*[a-z].*";
			return code.matches(n) && code.matches(a);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

}
