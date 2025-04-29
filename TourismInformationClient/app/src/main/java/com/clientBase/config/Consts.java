package com.clientBase.config;


public class Consts {

	public static int TIME_OUT = 30000;



	public final static String URL = "http://192.168.0.114:8080/TourismInformationService/";

	public final static String URL_IMAGE = "http://192.168.0.114:8080/TourismInformationService/upload/";

	public static class APP {

		public static final String RegisterAction = "servlet/RegisterAction";
		public static final String MessageAction = "servlet/MessageAction";

	}

	public static class actionId {
		public static final int resultFlag = 1;
		public static final int resultCode = 2;
		public static final int resultCollectOk = 11;
		public static final int resultCollectNo = 12;
		public static final int resultPraiseOk = 22;
		public static final int resultPraiseNo = 21;

	}
}
