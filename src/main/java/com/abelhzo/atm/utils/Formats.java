package com.abelhzo.atm.utils;

import java.text.DecimalFormat;

/**
 *
 * @autor: Abel_HZO
 * @company: AbelHZO
 * @created: 20/09/2018 18:49:46
 * @file: Formats.java
 * @license: <i>GNU General Public License<i>
 *
 */
public class Formats {

	public static String moneda(Double cantidad) {
		DecimalFormat df = null;
		if(cantidad != 0.0) {
			df = new DecimalFormat("$###,###,###.00");
		} else {
			df = new DecimalFormat("$0.00");
		}
		return df.format(cantidad);
	}

}
