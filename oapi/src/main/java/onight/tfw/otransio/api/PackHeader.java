package onight.tfw.otransio.api;

import onight.tfw.mservice.NodeHelper;

public class PackHeader {
	public final static String EXT_HIDDEN = "_";

	public final static String Set_COOKIE = "Set-Cookie";

	public final static String EXT_IGNORE = EXT_HIDDEN + "_ign";

	public final static String EXT_IGNORE_FORWARD = EXT_IGNORE + "f";
	public final static String EXT_IGNORE_RESPONSE = EXT_IGNORE + "b";

	public final static String PEER_IP = EXT_IGNORE_RESPONSE + "_peerip";
	
	
	public final static String FORWORD_URL = "_url";
	public final static String FORWORD_METHOD = "_method";
	
	public static String PACK_FROM = PackHeader.EXT_HIDDEN  + "f";
	public static String PACK_TO = PackHeader.EXT_HIDDEN  + "t";
//	public static String PACK_FROM_IDXX = PackHeader.EXT_HIDDEN + "_" + "from.idx";
//	public static String PACK_TO_IDX = PackHeader.EXT_HIDDEN + "_" + "to.idxx";
	public static String PACK_URI = PackHeader.EXT_HIDDEN + "u";

	
	// public final static String ENC = "enc";
	// public final static String CMD = "cmd";
	// public final static String FROM = "from";

	public static final String CookieDomain = NodeHelper.getPropInstance().get("http.cookie.domain", null);
	public static final String CookiePath = NodeHelper.getPropInstance().get("http.cookie.path", "/");
	public static final int CookieExpire = NodeHelper.getPropInstance().get("http.cookie.expire", 24 * 3600);
	// public final static String SIZE = "size";
	// public final static String TYPE = "type";// iq-->需要回复，message-->不需要回复
	// public final static String PROXY = "proxy";
	// public final static String SUBCMD = "subcmd";

	public final static String TO = "to";
	public final static String CMD_HB = "HBL***";

	public final static String REMOTE_LOGIN = "RLG***";
	public final static String REMOTE_LOGIN_RET = "BLG***";
	public final static String REMOTE_MODULE = "***";

	public final static String IQ = "iq";

	public final static String TTL = "ttl";

	public final static String WALL = "wall";
	public final static String WALL_ROUTE = "wr";

	public final static String MESSAGE = "message";

	// public final static String SESSION = EXT_IGNORE+"SSM";

	public final static String EXT_IGNORE_HTTP_REQUEST = EXT_IGNORE_RESPONSE + "_httprequest";
	public final static String EXT_IGNORE_HTTP_RESPONSE = EXT_IGNORE_RESPONSE + "_httpresponse";

	public final static String EXT_IGNORE_EXCEPTION = EXT_HIDDEN + "__exception";

	public final static String HTTP_PARAM_FIX_HEAD = "fh";
	public final static String HTTP_PARAM_BODY_DATA = "bd";
	public final static String GCMD = "gcmd";

	// public final static String H_IGN_GCMD = EXT_IGNORE_RESPONSE + "_gcmd";

	public final static byte[] EMPTY_BYTES = new byte[] {};

}
