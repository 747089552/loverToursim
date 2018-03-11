package com.aTourism.ZhouXY.base;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mybatis - 获取Mybatis查询sql工具
 *
 * @author liangck
 */
public class SqlHelper {

    /**
     * 日志
     *
     * */
    public static final MyLogger LOGGER = new MyLogger(SqlHelper.class);

    /**
     * 无参构造
     * */
    private SqlHelper() {
    }


    /**
     * 通过Mapper方法名获取sql
     *
     * @param session
     * @param fullMapperMethodName
     * @param args
     * @return
     */
    public static String getMapperSql(SqlSession session, String fullMapperMethodName, Object... args) {
        if (args == null || args.length == 0) {
            return getNamespaceSql(session, fullMapperMethodName, null);
        }
        String methodName = fullMapperMethodName.substring(fullMapperMethodName.lastIndexOf('.') + 1);
        Class mapperInterface = null;
        try {
            mapperInterface = Class.forName(fullMapperMethodName.substring(0, fullMapperMethodName.lastIndexOf('.')));
            return getMapperSql(session, mapperInterface, methodName, args);
        } catch (ClassNotFoundException e) {
            LOGGER.error("",e);
            throw new IllegalArgumentException("参数" + fullMapperMethodName + "无效！");
        }
    }

    /**
     * 通过Mapper接口和方法名
     *
     * @param session
     * @param mapperInterface
     * @param methodName
     * @param args
     * @return
     */
    public static String getMapperSql(SqlSession session, Class mapperInterface, String methodName, Object... args) {
        String fullMapperMethodName = mapperInterface.getCanonicalName() + "." + methodName;
        if (args == null || args.length == 0) {
            return getNamespaceSql(session, fullMapperMethodName, null);
        }
        Method method = getDeclaredMethods(mapperInterface, methodName);
        Map params = new HashMap();
        final Class<?>[] argTypes = method.getParameterTypes();
        for (int i = 0; i < argTypes.length; i++) {
            if (!RowBounds.class.isAssignableFrom(argTypes[i]) && !ResultHandler.class.isAssignableFrom(argTypes[i])) {
                String paramName = "param" + (params.size() + 1);
                paramName = getParamNameFromAnnotation(method, i, paramName);
                params.put(paramName, i >= args.length ? null : args[i]);
            }
        }
        if (args != null && args.length == 1) {
            Object wParams = wrapCollection(args[0]);
            if (wParams instanceof Map) {
                params.putAll((Map) wParams);
            }
        }
        return getNamespaceSql(session, fullMapperMethodName, params);
    }

    /**
     * 通过命名空间方式获取sql
     *
     * @param session
     * @param namespace
     * @return
     */
    public static String getNamespaceSql(SqlSession session, String namespace) {
        return getNamespaceSql(session, namespace, null);
    }

    /**
     * 通过命名空间方式获取sql
     *
     * @param session
     * @param namespace
     * @param params
     * @return
     */
    public static String getNamespaceSql(SqlSession session, String namespace, Object params) {
        Object paramsNew = params;
        paramsNew = wrapCollection(paramsNew);
        Configuration configuration = session.getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(namespace);
        BoundSql boundSql = mappedStatement.getBoundSql(paramsNew);
        String sql = boundSql.getSql();
        return formatSql(sql);
    }


    /**
     * 获取指定的方法
     *
     * @param clazz
     * @param methodName
     * @return
     */
    private static Method getDeclaredMethods(Class clazz, String methodName) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new IllegalArgumentException("方法" + methodName + "不存在！");
    }

    /**
     * 获取参数注解名
     *
     * @param method
     * @param i
     * @param paramName
     * @return
     */
    private static String getParamNameFromAnnotation(Method method, int i, String paramName) {
        String paramNameNew = paramName;
        final Object[] paramAnnos = method.getParameterAnnotations()[i];
        for (Object paramAnno : paramAnnos) {
            if (paramAnno instanceof Param) {
                paramNameNew = ((Param) paramAnno).value();
            }
        }
        return paramNameNew;
    }

    /**
     * 简单包装参数
     *
     * @param object
     * @return
     */
    private static Object wrapCollection(final Object object) {
        if (object instanceof List) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", object);
            return map;
        } else if (object != null && object.getClass().isArray()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("array", object);
            return map;
        }
        return object;
    }

    /**
     * 格式化sql
     * 
     * @param sql
     *            sql字符串
     * @return 格式化后的sql
     */
    public static String formatSql(String sql) {
        return sql == null ? "" : sql.replaceAll("\r|\n+", " ");
    }
}