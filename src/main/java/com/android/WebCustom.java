package com.android;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.simplifyqa.customMethod.SqaAutowired;
import com.simplifyqa.method.GeneralMethod;
import com.simplifyqa.method.WebMethod;



import org.apache.xmlbeans.impl.store.Locale;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
public class WebCustom {

@SqaAutowired
public Object current_object_web;

public boolean checkerce(){
    GeneralMethod ok = new GeneralMethod();
    ok.eq("param1","param2");
    return true;
}

public boolean datepickerakpk(String Date) {
		try {
            WebMethod obj = new WebMethod();
			String Udate = Date.split("/")[1];
			String Umonth = Date.split("/")[0];
			org.joda.time.format.DateTimeFormatter date = org.joda.time.format.DateTimeFormat.forPattern("MMMM");
			JSONObject idnamevalue;
			idnamevalue = obj.uniqueElement(obj.curObject);
			String identifiername = idnamevalue.getString("identifiername");
			String value = idnamevalue.getString("value");
			Date actdate = new Date(Date);
			DateTime dtOrg = new DateTime(actdate);
			String Tmonth = dtOrg.toString(date);
			String Uyear = Date.split("/")[2];
			obj.click();
			// calender xpath (mon yr)

			JSONObject elements = obj.findElements("xpath", "(//span[@class='flatpickr-day '])[1]").getJSONObject(0);
			String attribute = obj.findElements("xpath", "(//span[@class='flatpickr-day '])[1]").toString();
			Iterator<String> iterator = elements.keys();
			String key = null;
			if (elements.length() == 1) {
				while (iterator.hasNext()) {
					key = iterator.next();
				}
				String elementId = elements.getString(key);
				String innertext = null;
				int count = 0;
				// String month = null;
				String Fmonth = attribute.split(" ")[0];
				String Fyear = attribute.split(" ")[attribute.split(" ").length - 1];
				do {
					if (Fmonth.contains(Tmonth)) {
						int flag = 1;
						break;
					} else {
						// check for year
						while (!Fyear.equals(Uyear)) {
							if (Integer.parseInt(Uyear) > Integer.parseInt(Fyear)) {
								obj.click("xpath",
										"(//span[contains(@class,'flatpickr-prev-month')]/..//span[@class='arrowUp'])[1]");
								Thread.sleep(1000);
							} else if (Integer.parseInt(Uyear) < Integer.parseInt(Fyear)) {
								obj.click("xpath",
										"(//span[contains(@class,'flatpickr-prev-month')]/..//span[@class='arrowDown'])[1]");
								Thread.sleep(1000);
							} else
								break;
							// Fyear = getattribute("xpath", "(//span[@class='flatpickr-day '])[1]", "aria-label")
							// 		.split(" ")[attribute.split(" ").length - 1];
						}
						// check for month
						SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
						String dt = Tmonth + " " + Uyear;
						String dt1 = Fmonth + " " + Fyear;
						Date firstDate = sdf.parse(dt);
						Date secondDate = sdf.parse(dt1);
						while (!Fmonth.equals(Tmonth)) {
							if (firstDate.compareTo(secondDate) > 0) {
								obj.click("xpath",
										"(//span[contains(@class,'flatpickr-prev-month')]/../span[@class='flatpickr-next-month']//*[name()='svg'])[1]");
								Thread.sleep(2000);
							} else {
								obj.click("xpath",
										"(//span[contains(@class,'flatpickr-prev-month')]/../span[@class='flatpickr-prev-month']//*[name()='svg'])[1]");
								Thread.sleep(2000);
								// Thread.sleep(2000);
							}
							//Fmonth = obj.getattribute("xpath", "(//span[@class='flatpickr-day '])[1]", "aria-label")
							//		.split(" ")[0];
						}
					}
				} while (!Fmonth.contains(Tmonth));
				// date
				obj.click("xpath", "//div[contains(@class,'open')]//span[text()='" + Integer.parseInt(Udate)
						+ "' and not(contains(@class,'nextMonthDay') and contains(@class,'nextMonthDay'))]");
			}
			return true;
		} catch (Exception e) {
			// .customlogg(userlogger, "Step Failed Execute to datepicker : ", null, e.toString(), "error");
			System.out.println(e);
			return false;
		}
	}

   
}

