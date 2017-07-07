package com.wetool.push.server.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.wetool.push.api.model.MsgType;
import com.wetool.push.api.model.Result;
import com.wetool.push.api.model.S2ClientResp;
import com.wetool.push.api.model.client.CommodityReq;
import com.wetool.push.api.model.model.Commodity;
import com.wetool.push.api.model.server.CommodityResp;
import com.wetool.push.server.feign.CommodityFeignClient;
import com.wetool.push.server.model.Message;
import feign.Feign;

@Component
public class CommodityService {

    @Autowired
    Feign.Builder builder;

    //@Value("${url.commodity-server}")
    private String url;

    public CommodityResp commSync(CommodityReq commodityReq) throws Exception {
//        try {
        url = "http://127.0.0.1:16010";
        Boolean flag = true;
            /* 商品获取接口 */
        CommodityFeignClient commodityFeignClient = builder.target(CommodityFeignClient.class, url);
            /* 获取接口返回数据 */
        ResponseEntity<Message<?>> resp = commodityFeignClient.list(commodityReq.getUpdateDate(), commodityReq.getMerchantId(),
                commodityReq.getSize());
        System.out.println(resp.getBody());
        Message message = resp.getBody();
			/* 数据对象 */
        LinkedHashMap obj = (LinkedHashMap) message.getData();
			/* 商品信息数据集合 */
        List<Commodity> commoditys = (List) obj.get("content");
			/* 获取分页对象 */
        LinkedHashMap pages = (LinkedHashMap) obj.get("page");
			/* 获取商品信息提啊件查询总记录数 */
        Integer totalElements = (Integer) pages.get("totalElements");
			/* 判断是否获取全部查询信息 */
        if (commodityReq.getSize() < totalElements) {
            flag = false;
        }
        CommodityResp commodityResp = null;
//            switch (message.getCode()) {
//                case 0: // 成功
        S2ClientResp s2ClientResp = new S2ClientResp(MsgType.COMMODITY_RESP, Result.SUCCESS);
        //commodityResp = new CommodityResp(MsgType.COMMODITY_RESP, Result.SUCCESS);
        commodityResp = new CommodityResp();
        String json = "[{id=1171, merchantId=1, name=隆力奇男士组合装, categoryId=26, barcode=6900077001754, pinyin=longliqinanshizuhezhuang, pinyinShorthand=llqnszhz, type=null, picPath=, resources=null, specification=, unit=盒, description=null, sellingPrice=35.0, buyingPrice=null, createDate=2016-12-26, updateDate=1499333562000, isGs1=false, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1193, merchantId=1, name=公仔猪肉浓汤碗仔面82g, categoryId=28, barcode=6900082020252, pinyin=gongzizhurounongtangwanzimian82g, pinyinShorthand=gzzrntwzm82g, type=null, picPath=, resources=null, specification=, unit=82g, description=null, sellingPrice=4.5, buyingPrice=null, createDate=2016-12-27, updateDate=1499333511000, isGs1=true, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1201, merchantId=1, name=如丰香辣萝卜400g, categoryId=31, barcode=6900631212305, pinyin=rufengxianglaluobo400g, pinyinShorthand=rfxllb400g, type=null, picPath=, resources=null, specification=, unit=400g, description=null, sellingPrice=7.0, buyingPrice=null, createDate=2016-12-27, updateDate=1499333533000, isGs1=true, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1335, merchantId=1, name=灭害灵杀虫剂, categoryId=21, barcode=6901064000194, pinyin=miehailingshachongji, pinyinShorthand=mhlscj, type=null, picPath=, resources=null, specification=600ml, unit=瓶, description=null, sellingPrice=15.0, buyingPrice=null, createDate=2016-11-22, updateDate=1499333870000, isGs1=false, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1366, merchantId=1, name=安安干爽洁净1, categoryId=2, barcode=6901123218027, pinyin=ananganshuangjiejing1, pinyinShorthand=aagsjj1, type=null, picPath=, resources=null, specification=, unit=150g, description=null, sellingPrice=4.5, buyingPrice=null, createDate=2016-12-12, updateDate=1499332378000, isGs1=true, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1383, merchantId=1, name=嘉士利果乐果香饼干205克, categoryId=9, barcode=6901180211986, pinyin=jiashiliguoleguoxiangbinggan205ke, pinyinShorthand=jslglgxbg205k, type=null, picPath=, resources=null, specification=, unit=袋, description=null, sellingPrice=6.0, buyingPrice=null, createDate=2016-11-26, updateDate=1499334529000, isGs1=false, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1494, merchantId=1, name=500ml金六福四星, categoryId=35, barcode=6901382105380, pinyin=null, pinyinShorthand=null, type=null, picPath=, resources=null, specification=500ml, unit=盒, description=, sellingPrice=50.0, buyingPrice=null, createDate=2016-11-11, updateDate=1427875139000, isGs1=false, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1495, merchantId=1, name=475ml金六福三星酒, categoryId=35, barcode=6901382106523, pinyin=null, pinyinShorthand=null, type=null, picPath=, resources=null, specification=475ml, unit=瓶, description=, sellingPrice=35.5, buyingPrice=null, createDate=2016-11-11, updateDate=1404201534000, isGs1=false, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1497, merchantId=1, name=上海清凉皂, categoryId=27, barcode=6901404310952, pinyin=null, pinyinShorthand=null, type=null, picPath=, resources=null, specification=95g, unit=块, description=, sellingPrice=3.5, buyingPrice=null, createDate=2016-05-20, updateDate=1435737522000, isGs1=false, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1499, merchantId=1, name=万士发杏仁通心酥饼, categoryId=27, barcode=6901533121689, pinyin=null, pinyinShorthand=null, type=null, picPath=, resources=null, specification=168g, unit=包, description=, sellingPrice=2.8, buyingPrice=null, createDate=2016-05-20, updateDate=1493625486000, isGs1=true, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1561, merchantId=1, name=大白菜哈哈, categoryId=17, barcode=0988412, pinyin=dabaicaihaha, pinyinShorthand=dbchh, type=2, picPath=null, resources={id=153, isBanner=0, type=0, resUrl=http://wetool.oss-cn-beijing.aliyuncs.com/ms.commodity.dev/cf27049a-b958-4b77-8002-b68e819fe8db_w800_h356.jpg, width=800.0, heigth=356.0}, specification=1000g, unit=千克, description=null, sellingPrice=1.0, buyingPrice=1.0, createDate=2017-07-03, updateDate=1499334550000, isGs1=null, country=OTHER, splitRule=null, warningNumber=null, inventoryQuantity=100.0, deleted=false, detachable=false, links=[]}, {id=1568, merchantId=1, name=测试商品aa, categoryId=3, barcode=6901231231231, pinyin=ceshishangpinaa, pinyinShorthand=csspaa, type=1, picPath=null, resources=null, specification=, unit=, description=null, sellingPrice=1.5, buyingPrice=null, createDate=2017-07-06, updateDate=1499334520000, isGs1=null, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1570, merchantId=1, name=苹果, categoryId=97, barcode=0001, pinyin=pingguo, pinyinShorthand=pg, type=2, picPath=null, resources=null, specification=, unit=, description=null, sellingPrice=5.98, buyingPrice=2.98, createDate=2017-07-06, updateDate=1499338922000, isGs1=null, country=OTHER, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1571, merchantId=1, name=米米果果水杯, categoryId=14, barcode=6921390375490, pinyin=mimiguoguoshuibei, pinyinShorthand=mmggsb, type=1, picPath=null, resources=null, specification=, unit=个, description=null, sellingPrice=15.9, buyingPrice=7.58, createDate=2017-07-06, updateDate=1499339284000, isGs1=null, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=100.0, deleted=false, detachable=false, links=[]}, {id=1572, merchantId=1, name=米米果果水杯(箱), categoryId=14, barcode=6921390375490, pinyin=mimiguoguoshuibei(xiang), pinyinShorthand=mmggsb(x), type=1, picPath=null, resources=null, specification=1*10, unit=箱, description=null, sellingPrice=149.0, buyingPrice=65.8, createDate=2017-07-06, updateDate=1499339450000, isGs1=null, country=CN, splitRule={id=106, commodityId=1571, splitNumber=10.0}, warningNumber=null, inventoryQuantity=100.0, deleted=false, detachable=true, links=[]}, {id=1573, merchantId=1, name=软件, categoryId=98, barcode=123456786699999999, pinyin=ruanjian, pinyinShorthand=rj, type=2, picPath=null, resources=null, specification=, unit=, description=null, sellingPrice=5.98, buyingPrice=null, createDate=2017-07-06, updateDate=1499340228000, isGs1=null, country=OTHER, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1574, merchantId=1, name=小明, categoryId=21, barcode=1234567, pinyin=xiaoming, pinyinShorthand=xm, type=1, picPath=null, resources=null, specification=, unit=, description=null, sellingPrice=3.0, buyingPrice=1.0, createDate=2017-07-06, updateDate=1499340639000, isGs1=null, country=OTHER, splitRule=null, warningNumber=null, inventoryQuantity=10.0, deleted=false, detachable=false, links=[]}, {id=1576, merchantId=1, name=测试商品aa19, categoryId=20, barcode=6901231231231, pinyin=ceshishangpinaa19, pinyinShorthand=csspaa19, type=1, picPath=null, resources=null, specification=, unit=, description=null, sellingPrice=1.5, buyingPrice=null, createDate=2017-07-06, updateDate=1499342067000, isGs1=null, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}, {id=1577, merchantId=1, name=测试商品aa10, categoryId=27, barcode=6901231231231, pinyin=ceshishangpinaa10, pinyinShorthand=csspaa10, type=1, picPath=null, resources=null, specification=, unit=, description=null, sellingPrice=1.5, buyingPrice=0.5, createDate=2017-07-06, updateDate=1499342106000, isGs1=null, country=CN, splitRule=null, warningNumber=null, inventoryQuantity=0.0, deleted=false, detachable=false, links=[]}]";
        
		commodityResp.setCommoditys(commoditys.get(0));
        commodityResp.setFlag(flag);
        System.out.println("返回客户端------->" + commodityResp);
//				s2ClientResp.setData(commodityResp);
        return commodityResp;
/*                case 1:// 操作失败
                    System.out.println("商品信息查询【 " + commodityReq.getSize() + "】条数据失败！ ");
                    return new S2ClientResp(MsgType.COMMODITY_RESP, Result.SUCCESS);
                case 19: // 商家未登录
                    System.out.println("商品信息同步【 " + commodityReq.getSize() + "】条数据失败！，商家未登录！ ");
                    return new S2ClientResp(MsgType.COMMODITY_RESP, Result.SUCCESS);
                case 18: // 时间格式错误
                    System.out.println("商品信息同步【 " + commodityReq.getSize() + "】条数据失败！，商家时间格式错误！ ");
                    return new S2ClientResp(MsgType.COMMODITY_RESP, Result.SUCCESS);
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("商品信息同步【 " + commodityReq.getSize() + "】条数据失败！ ");
            return new S2ClientResp(MsgType.COMMODITY_RESP, Result.SUCCESS);
        }*/
    }
}