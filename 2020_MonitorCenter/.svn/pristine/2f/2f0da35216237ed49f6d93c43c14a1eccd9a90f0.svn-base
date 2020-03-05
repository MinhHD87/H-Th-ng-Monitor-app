/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.util;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import vn.htc.monitor.common.Tool;

/**
 *
 * @author HTC-PC
 */
public class RouteMonitor {
    
    public static RouteMonitor json2Object(String str) {
        RouteMonitor result = new RouteMonitor();
        if (!Tool.checkNull(str)) {
            try {
                JSONObject obj = (JSONObject) JSONSerializer.toJSON(str);
                
                try {
                    JSONObject mstt1Json = (JSONObject) obj.get("mstt1");
                    MoniterStatusValue mstt1 = (MoniterStatusValue) JSONObject.toBean(mstt1Json, MoniterStatusValue.class);
                    result.setMstt1(mstt1);
                } catch (Exception e) {
                    Tool.debug("Route khong co mstt1:");
                }
                
                try {
                    JSONObject mstt2Json = (JSONObject) obj.get("mstt2");
                    MoniterStatusValue mstt2 = (MoniterStatusValue) JSONObject.toBean(mstt2Json, MoniterStatusValue.class);
                    result.setMstt2(mstt2);
                } catch (Exception e) {
                    Tool.debug("Route khong co mstt2:");
                }
                
                try {
                    JSONObject mstt3Json = (JSONObject) obj.get("mstt3");
                    MoniterStatusValue mstt3 = (MoniterStatusValue) JSONObject.toBean(mstt3Json, MoniterStatusValue.class);
                    result.setMstt3(mstt3);
                } catch (Exception e) {
                    Tool.debug("Route khong co mstt3:");
                }
                
                try {
                    JSONObject mstt4Json = (JSONObject) obj.get("mstt4");
                    MoniterStatusValue mstt4 = (MoniterStatusValue) JSONObject.toBean(mstt4Json, MoniterStatusValue.class);
                    result.setMstt4(mstt4);
                } catch (Exception e) {
                    Tool.debug("Route khong co mstt4:");
                }
                
                try {
                    JSONObject mstt5Json = (JSONObject) obj.get("mstt5");
                    MoniterStatusValue mstt5 = (MoniterStatusValue) JSONObject.toBean(mstt5Json, MoniterStatusValue.class);
                    result.setMstt5(mstt5);
                } catch (Exception e) {
                    Tool.debug("Route table khong co mstt5:");
                }
                
                try {
                    JSONObject mstt6Json = (JSONObject) obj.get("mstt6");
                    MoniterStatusValue mstt6 = (MoniterStatusValue) JSONObject.toBean(mstt6Json, MoniterStatusValue.class);
                    result.setMstt6(mstt6);
                } catch (Exception e) {
                    Tool.debug("Route khong co mstt6:");
                }
                
                try {
                    JSONObject mstt7Json = (JSONObject) obj.get("mstt7");
                    MoniterStatusValue mstt7 = (MoniterStatusValue) JSONObject.toBean(mstt7Json, MoniterStatusValue.class);
                    result.setMstt7(mstt7);
                } catch (Exception e) {
                    Tool.debug("Route khong co mstt7:");
                }
                
                try {
                    JSONObject mstt8Json = (JSONObject) obj.get("mstt8");
                    MoniterStatusValue mstt8 = (MoniterStatusValue) JSONObject.toBean(mstt8Json, MoniterStatusValue.class);
                    result.setMstt8(mstt8);
                } catch (Exception e) {
                    Tool.debug("Route khong co mstt8:");
                }
                
                try {
                    JSONObject mstt9Json = (JSONObject) obj.get("mstt9");
                    MoniterStatusValue mstt9 = (MoniterStatusValue) JSONObject.toBean(mstt9Json, MoniterStatusValue.class);
                    result.setMstt9(mstt9);
                } catch (Exception e) {
                    Tool.debug("Route khong co mstt9:");
                }
                
                try {
                    JSONObject mstt10Json = (JSONObject) obj.get("mstt10");
                    MoniterStatusValue mstt10 = (MoniterStatusValue) JSONObject.toBean(mstt10Json, MoniterStatusValue.class);
                    result.setMstt10(mstt10);
                } catch (Exception e) {
                    Tool.debug("Route khong co mstt10:");
                }
                
                try {
                    JSONObject numJson = (JSONObject) obj.get("num");
                    MoniterStatusValue num = (MoniterStatusValue) JSONObject.toBean(numJson, MoniterStatusValue.class);
                    result.setNum(num);
                } catch (Exception e) {
                    Tool.debug("Route khong co num:");
                }
                
            } catch (Exception e) {
                Tool.debug("String json Route table not valid");
            }
        }

        return result;
    }
    
    public static String toStringJson(RouteMonitor route) {
        if (route != null) {
            JSONObject obj = JSONObject.fromObject(route);
            String str = obj.toString();
            return str;
        } else {
            return "";
        }
    }
    
    public String toStringJson2(RouteMonitor route) {
        if (route != null) {
            JSONObject obj = JSONObject.fromObject(route);
            String str = obj.toString();
            return str;
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "RouteMonitor{" + "mstt1=" + mstt1 + ", mstt2=" + mstt2 + ", mstt3=" + mstt3 + ", mstt4=" + mstt4 + ", mstt5=" + mstt5 + ", mstt6=" + mstt6 + ", mstt7=" + mstt7 + ", mstt8=" + mstt8 + ", mstt9=" + mstt9 + ", mstt10=" + mstt10 + ", num=" + num + '}';
    }
          
    private MoniterStatusValue mstt1;
    private MoniterStatusValue mstt2;
    private MoniterStatusValue mstt3;
    private MoniterStatusValue mstt4;
    private MoniterStatusValue mstt5;
    private MoniterStatusValue mstt6;
    private MoniterStatusValue mstt7;
    private MoniterStatusValue mstt8;
    private MoniterStatusValue mstt9;
    private MoniterStatusValue mstt10;
    private MoniterStatusValue num;

    public RouteMonitor() {
        this.mstt1 = new MoniterStatusValue();
        this.mstt2 = new MoniterStatusValue();
        this.mstt3 = new MoniterStatusValue();
        this.mstt4 = new MoniterStatusValue();
        this.mstt5 = new MoniterStatusValue();
        this.mstt6 = new MoniterStatusValue();
        this.mstt7 = new MoniterStatusValue();
        this.mstt8 = new MoniterStatusValue();
        this.mstt9 = new MoniterStatusValue();
        this.mstt10 = new MoniterStatusValue();
        this.num = new MoniterStatusValue();
    }

    public MoniterStatusValue getNum() {
        if (num == null) {
            num = new MoniterStatusValue();
        }
        return num;
    }

    public void setNum(MoniterStatusValue num) {
        this.num = num;
    }

    public MoniterStatusValue getMstt1() {
        if (mstt1 == null) {
            mstt1 = new MoniterStatusValue();
        }
        return mstt1;
    }

    public void setMstt1(MoniterStatusValue mstt1) {
        this.mstt1 = mstt1;
    }

    public MoniterStatusValue getMstt2() {
        if (mstt2 == null) {
            mstt2 = new MoniterStatusValue();
        }
        return mstt2;
    }

    public void setMstt2(MoniterStatusValue mstt2) {
        this.mstt2 = mstt2;
    }

    public MoniterStatusValue getMstt3() {
        if (mstt3 == null) {
            mstt3 = new MoniterStatusValue();
        }
        return mstt3;
    }

    public void setMstt3(MoniterStatusValue mstt3) {
        this.mstt3 = mstt3;
    }

    public MoniterStatusValue getMstt4() {
        if (mstt4 == null) {
            mstt4 = new MoniterStatusValue();
        }
        return mstt4;
    }

    public void setMstt4(MoniterStatusValue mstt4) {
        this.mstt4 = mstt4;
    }

    public MoniterStatusValue getMstt5() {
        if (mstt5 == null) {
            mstt5 = new MoniterStatusValue();
        }
        return mstt5;
    }

    public void setMstt5(MoniterStatusValue mstt5) {
        this.mstt5 = mstt5;
    }

    public MoniterStatusValue getMstt6() {
        if (mstt6 == null) {
            mstt6 = new MoniterStatusValue();
        }
        return mstt6;
    }

    public void setMstt6(MoniterStatusValue mstt6) {
        this.mstt6 = mstt6;
    }

    public MoniterStatusValue getMstt7() {
        if (mstt7 == null) {
            mstt7 = new MoniterStatusValue();
        }
        return mstt7;
    }

    public void setMstt7(MoniterStatusValue mstt7) {
        this.mstt7 = mstt7;
    }

    public MoniterStatusValue getMstt8() {
        if (mstt8 == null) {
            mstt8 = new MoniterStatusValue();
        }
        return mstt8;
    }

    public void setMstt8(MoniterStatusValue mstt8) {
        this.mstt8 = mstt8;
    }

    public MoniterStatusValue getMstt9() {
        if (mstt9 == null) {
            mstt9 = new MoniterStatusValue();
        }
        return mstt9;
    }

    public void setMstt9(MoniterStatusValue mstt9) {
        this.mstt9 = mstt9;
    }

    public MoniterStatusValue getMstt10() {
        if (mstt10 == null) {
            mstt10 = new MoniterStatusValue();
        }
        return mstt10;
    }

    public void setMstt10(MoniterStatusValue mstt10) {
        this.mstt10 = mstt10;
    }

    public void setMstt1(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
