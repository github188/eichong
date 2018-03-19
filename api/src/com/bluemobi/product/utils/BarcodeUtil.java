package com.bluemobi.product.utils;

import com.barcodelib.barcode.Linear;

import java.awt.*;

public class BarcodeUtil {

	public static void main(String[] args) throws Exception {
		createIdentCode("56400000004", "c://codabar.gif");
		//barcode("c://codabar.gif",Linear.CODE128);
	}

	public static void createIdentCode(String content, String imgPath) {
		Linear barcode = new Linear();
		barcode.setType(Linear.CODE128);
		// barcode data to encode
		// should be 11 digits
		barcode.setData(content);

		// unit of measure for X, Y, LeftMargin, RightMargin, TopMargin,
		// BottomMargin
		barcode.setUOM(Linear.UOM_PIXEL);
		// barcode module width in pixel
		barcode.setX(3f);
		// barcode module height in pixel
		barcode.setY(75f);

		barcode.setLeftMargin(0f);
		barcode.setRightMargin(0f);
		barcode.setTopMargin(0f);
		barcode.setBottomMargin(0f);
		// barcode image resolution in dpi
		barcode.setResolution(72);

		// disply human readable text under the barcode
		barcode.setShowText(true);
		// human reable text font style
		barcode.setTextFont(new Font("Arial", Font.BOLD, 16));
		// ANGLE_0, ANGLE_90, ANGLE_180, ANGLE_270
		barcode.setRotate(Linear.ANGLE_0);
		try {
			barcode.renderBarcode(imgPath);
		} catch (Exception e) {
		}
	}

	public enum Type {
		CODABAR, CODE11, UOM_PIXEL, CODE2OF5, CODE39, CODE39EX, CODE93, EAN8, EAN8_2, EAN8_5, EAN13, EAN13_2, EAN13_5, ISBN, ISBN_5, ISSN, ISSN_2, ITF14, INTERLEAVED25, IDENTCODE, LEITCODE, MSI, ONECODE, PLANET, POSTNET, RM4SCC, UPCA, UPCA_2, UPCA_5, UPCE, UPCE_2, UPCE_5, CODE128, EAN128
	}

	/**
	 * 条形码
	 * 
	 * @throws Exception
	 */
	public static void barcode(String file, int type) throws Exception {
		Linear barcode = new Linear();
		barcode.setType(type);
		barcode.setData("12345678901");
		barcode.setN(3);
		barcode.setUOM(Linear.UOM_PIXEL);
		barcode.setX(3f);
		barcode.setY(75f);
		barcode.setLeftMargin(0f);
		barcode.setRightMargin(0f);
		barcode.setTopMargin(0f);
		barcode.setBottomMargin(0f);
		barcode.setResolution(72);
		barcode.setShowText(true);
		barcode.setTextFont(new Font("Arial", 0, 12));
		barcode.setRotate(Linear.ANGLE_0);
		barcode.renderBarcode(file);
	}
}
