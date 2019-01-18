package dao.impl;

import dao.OMConfigureDao;

public class OMConfigureDaoImpl implements OMConfigureDao {

    @Override
    public boolean setExt(){ //设置分机配置
        return false;
    }


    @Override
    public boolean setExtGroup(){
        String xml1 = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Control attribute=\"Assign\">\n" +
                "<group id=\"1\">\n" +
                "    <voicefile>NewMorning</voicefile>\n" +
                "    <distribution>sequential</distribution>\n" +
                "    <ext>211</ext>\n" +
                "    <ext>212</ext>\n" +
                "    <ext>213</ext>\n" +
                "    <ext>214</ext>\n" +
                "    <ext>215</ext>\n" +
                "    <ext>216</ext>\n" +
                "</group>\n" +
                "</Control>";

        String xml2 = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Control attribute=\"Assign\">\n" +
                "<group id=\"2\">\n" +
                "    <voicefile>NewMorning</voicefile>\n" +
                "    <distribution>sequential</distribution>\n" +
                "    <ext>217</ext>\n" +
                "    <ext>218</ext>\n" +
                "    <ext>219</ext>\n" +
                "    <ext>220</ext>\n" +
                "    <ext>221</ext>\n" +
                "    <ext>222</ext>\n" +
                "</group>\n" +
                "</Control>";

        String xml3 = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Control attribute=\"Assign\">\n" +
                "<group id=\"11\">\n" +
                "    <voicefile>NewMorning</voicefile>\n" +
                "    <distribution>sequential</distribution>\n" +
                "    <ext>227</ext>\n" +
                "    <ext>228</ext>\n" +
                "</group>\n" +
                "</Control>";

        String xml4 = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Control attribute=\"Assign\">\n" +
                "<group id=\"12\">\n" +
                "    <voicefile>NewMorning</voicefile>\n" +
                "    <distribution>sequential</distribution>\n" +
                "    <ext>229</ext>\n" +
                "    <ext>230</ext>\n" +
                "</group>\n" +
                "</Control>";

        OMTransferBase.omTransferBase("配置分机组1", xml1);
        OMTransferBase.omTransferBase("配置分机组2", xml2);
        OMTransferBase.omTransferBase("配置分机组3", xml3);
        OMTransferBase.omTransferBase("配置分机组4", xml4);

        return true;
    }

}
