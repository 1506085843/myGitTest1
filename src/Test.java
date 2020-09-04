
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.collections4.CollectionUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ROUND_UNNECESSARY;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class Test {
    public static void main(String[] args) throws Exception {
       /* List<String> oneList = new ArrayList<String>();
        oneList.add("1");
        oneList.add("2");
        oneList.add("3");

        List<String> twoList = new ArrayList<String>();
        twoList.addAll(oneList);

        oneList.add("4");
        for(String str : twoList){
            System.out.println("two:"+oneList);
        }*/

        String str1 = "{\"resourceId\":\"dfead70e4ec5c11e43514000ced0cdcaf\",\"properties\":{\"process_id\":\"process4\",\"name\":\"\",\"documentation\":\"\",\"processformtemplate\":\"\"}}";
        String tmp = StringEscapeUtils.unescapeEcmaScript(str1);
        System.out.println("tmp:" + tmp);

    }


    public static boolean isValidSudoku(char[][] board) {
        boolean bo = true;


        return bo;
    }


}



