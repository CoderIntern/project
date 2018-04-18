package consumer.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * <p> Date             :2018/4/12 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public class ProtoStuffSerializerUtil {

    private ProtoStuffSerializerUtil() {
    }

    /**
     * 序列化单个对象
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T obj){
        if (null == obj) {
            throw new RuntimeException("序列化对象不能为空:" + obj);
        }
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(obj.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
        byte[] protostuff = null;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new RuntimeException("序列化->" + obj.getClass() + "对象" + obj + "发生异常", e);
        } finally {
            buffer.clear();
        }
        return protostuff;
    }

    /**
     * 反序列化单个对象
     * @param paramArrayOfByte
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] paramArrayOfByte, Class<T> targetClass) {
        if (null == paramArrayOfByte || paramArrayOfByte.length == 0) {
            throw new RuntimeException("反序列化对象发生异常，byte序列为空");
        }
        T instance = null;
        try {
            instance = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("反序列化过程中一句类型创建对象失败", e);
        }
        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        ProtostuffIOUtil.mergeFrom(paramArrayOfByte, instance, schema);
        return instance;
    }

    /**
     * 序列化对象集合(List)
     * @param objList
     * @param <T>
     * @return
     */
    public static <T> byte[] serializeList(List<T> objList) {
        if (null == objList || objList.isEmpty()) {
            throw new RuntimeException("序列化对象列表" + objList + "参数异常");
        }
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(objList.get(0).getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
        byte[] protostuff = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ProtostuffIOUtil.writeListTo(bos, objList, schema, buffer);
            protostuff = bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("序列化对象列表" + objList + "发生异常", e);
        } finally {
            buffer.clear();
        }
        return protostuff;
    }

    /**
     * 反序列化对象集合(List)
     * @param <T>
     * @param paramArrayOfByte
     * @param targetClass
     * @return
     */
    public static <T> List<T> deserializeList(byte[] paramArrayOfByte, Class<T>targetClass) {
        if (null == paramArrayOfByte || paramArrayOfByte.length == 0) {
            throw new RuntimeException("反序列化对象发生异常,byte序列为空");
        }
        Schema<T> schema = RuntimeSchema.getSchema((Class<T>) targetClass);
        List<T> result = null;
        try {
            result = ProtostuffIOUtil.parseListFrom(new ByteArrayInputStream(paramArrayOfByte), schema);
        } catch (IOException e) {
            throw new RuntimeException("反序列化对象列表发生异常", e);
        }
        return result;
    }

}
