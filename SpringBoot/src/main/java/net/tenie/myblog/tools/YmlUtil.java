package net.tenie.myblog.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YmlUtil {

    /**
     * key:文件名索引
     * value:配置文件内容
     */
    private static Map<String, LinkedHashMap> ymls = new HashMap<>();

    /**
     * string:当前线程需要查询的文件名
     */
    private static ThreadLocal<String> nowFileName = new ThreadLocal<>();

    /**
     * 加载配置文件
     * @param fileName
     */
    public static void loadYml(String fileName) {
        nowFileName.set(fileName);
        if (!ymls.containsKey(fileName)) {
            ymls.put(fileName, new Yaml().loadAs(YmlUtil.class.getResourceAsStream("/" + fileName), LinkedHashMap.class));
        }
    }

    public static Object getValue(String key) throws Exception {
        // 首先将key进行拆分
        String[] keys = key.split("[.]");

        // 将配置文件进行复制
        Map ymlInfo = (Map) ymls.get(nowFileName.get()).clone();
        for (int i = 0; i < keys.length; i++) {
            Object value = ymlInfo.get(keys[i]);
            if (i < keys.length - 1) {
                ymlInfo = (Map) value;
            } else if (value == null) {
                throw new Exception("key不存在");
            } else {
                return value;
            }
        }
        throw new RuntimeException("不可能到这里的...");
    }

    public static Object getValue(String fileName, String key) throws Exception {
        // 首先加载配置文件
        loadYml(fileName);
        return getValue(key);
    }

	public void updateYamlFile() throws IOException {
		String src = YmlUtil.class.getClassLoader().getResource("application.yaml").getPath();
		Yaml yaml = new Yaml();
		FileWriter fileWriter;
		FileInputStream fileInputStream = new FileInputStream(new File(src));
		Map<String, Object> yamlMap = yaml.load(fileInputStream);
		Map<String, Object> esMap = (Map<String, Object>) yamlMap.get("elasticsearch");
		esMap.put("password", "password");
		// 字符输出
		fileWriter = new FileWriter(new File(src));
		// 用yaml方法把map结构格式化为yaml文件结构
		fileWriter.write(yaml.dumpAsMap(yamlMap));
		// 刷新
		fileWriter.flush();
		// 关闭流
		fileWriter.close();
		fileInputStream.close();
	} 
}

