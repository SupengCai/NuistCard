package com.linklife.web.base.utils;

/**
 * <p>
 * Base64.java
 * </p>
 * 
 * <pre>
 * Base64编码类
 * </pre>
 * 
 * @author caisupeng
 */
public class Base64 {

	public static final String KEYSTR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";


	public static String encode( String input ) {

		StringBuilder output = new StringBuilder();
		char chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		int i = 0;

		input = utf8Encode( input );

		while( i < input.length() ) {

			chr1 = input.charAt( i++ );
			chr2 = input.charAt( i++ );
			chr3 = input.charAt( i++ );

			enc1 = ( char )( chr1 >> 2 );
			enc2 = ( char )( ( ( chr1 & 3 ) << 4 ) | ( chr2 >> 4 ) );
			enc3 = ( char )( ( ( chr2 & 15 ) << 2 ) | ( chr3 >> 6 ) );
			enc4 = ( char )( chr3 & 63 );

			if( !Character.isDigit( chr2 ) ) {
				enc3 = enc4 = 64;
			} else if( !Character.isDigit( chr3 ) ) {
				enc4 = 64;
			}

			output.append( KEYSTR.charAt( enc1 ) );
			output.append( KEYSTR.charAt( enc2 ) );
			output.append( KEYSTR.charAt( enc3 ) );
			output.append( KEYSTR.charAt( enc4 ) );
		}
		return output.toString();
	}


	public static String Decode(String input ){
		StringBuilder output = new StringBuilder();
	        char chr1, chr2, chr3;
	        int enc1, enc2, enc3, enc4;
	        int i = 0;

	        while (i < input.length()) {

	            enc1 = KEYSTR.indexOf(input.charAt(i++));
	            enc2 = KEYSTR.indexOf(input.charAt(i++));
	            enc3 = KEYSTR.indexOf(input.charAt(i++));
	            enc4 = KEYSTR.indexOf(input.charAt(i++));

	            chr1 = (char)((enc1 << 2) | (enc2 >> 4));
	            chr2 = (char)(((enc2 & 15) << 4) | (enc3 >> 2));
	            chr3 = (char)(((enc3 & 3) << 6) | enc4);

	            output.append( chr1);

	            if (enc3 != 64) {
	            	 output.append( chr2);
	            }
	            if (enc4 != 64) {
	            	 output.append( chr3);
	            }

	        }

	        return utf8Decode(output.toString());
	}


	private static String utf8Encode( String string ) {

		StringBuilder utftext = new StringBuilder();

		for( int n = 0; n < string.length(); n++ ) {

			char c = string.charAt( n );
			if( c < 128 ) {
				utftext.append( c );
			} else if( ( c > 127 ) && ( c < 2048 ) ) {
				utftext.append( ( c >> 6 ) | 192 );
				utftext.append( ( c & 63 ) | 128 );
			} else {
				utftext.append( ( c >> 12 ) | 224 );
				utftext.append( ( ( c >> 6 ) & 63 ) | 128 );
				utftext.append( ( c & 63 ) | 128 );
			}
		}
		return utftext.toString();
	}


	private static String utf8Decode( String utftext ) {

		StringBuilder string = new StringBuilder();
		int i = 0;
		char c, c2, c3 = 0;

		while( i < utftext.length() ) {

			c = utftext.charAt( i );

			if( c < 128 ) {
				string.append( c );
				i++;
			} else if( ( c > 191 ) && ( c < 224 ) ) {
				c2 = utftext.charAt( i + 1 );
				string.append( ( ( c & 31 ) << 6 ) | ( c2 & 63 ) );
				i += 2;
			} else {
				c2 = utftext.charAt( i + 1 );
				c3 = utftext.charAt( i + 2 );
				string.append( ( ( c & 15 ) << 12 ) | ( ( c2 & 63 ) << 6 ) | ( c3 & 63 ) );
				i += 3;
			}
		}

		return string.toString();
	}

}
