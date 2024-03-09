package mluts.kebabcloud.jdbcRepositoryInterfaces;

import mluts.kebabcloud.domain.KebabOrder;

public interface OrderRepository {
    KebabOrder save(KebabOrder order);
}
