package cn.liaocp.amireux.core.domain;

import java.time.Instant;

/**
 * @author Chunping.Liao
 */
public interface AuditorDomain {

    Instant getCreateTime();

    String getCreateUser();

    Instant getUpdateTime();

    String getUpdateUser();


}
