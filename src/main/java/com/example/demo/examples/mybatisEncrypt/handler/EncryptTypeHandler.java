package com.example.demo.examples.mybatisEncrypt.handler;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.example.demo.examples.mybatisEncrypt.domain.Encrypt;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: liming522
 * @description: https://blog.csdn.net/fu_huo_1993/article/details/116996282
 * @date: 2022/8/2 3:15 PM
 * @hope: The newly created file will not have a bug
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Encrypt.class)
public class EncryptTypeHandler extends BaseTypeHandler<Encrypt> {

    // 自定义加密key秘钥
    private static final byte[] KEYS = "12345678abcdefgh".getBytes(StandardCharsets.UTF_8);

    /**
     * 设置参数
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Encrypt parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null || parameter.getValue() == null) {
            ps.setString(i, null);
            return;
        }
        AES aes = SecureUtil.aes(KEYS);
        String encrypt = aes.encryptHex(parameter.getValue());
        ps.setString(i, encrypt);
    }

    /**
     * 获取值
     */
    @Override
    public Encrypt getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return decrypt(rs.getString(columnName));
    }

    /**
     * 获取值
     */
    @Override
    public Encrypt getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return decrypt(rs.getString(columnIndex));
    }

    /**
     * 获取值
     */
    @Override
    public Encrypt getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return decrypt(cs.getString(columnIndex));
    }

    public Encrypt decrypt(String value) {
        if (null == value) {
            return null;
        }
        return new Encrypt(SecureUtil.aes(KEYS).decryptStr(value));
    }
}
