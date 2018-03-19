package com.wanma.ims.mapper;

import java.util.List;

import com.wanma.ims.common.domain.OrderDO;
import com.wanma.ims.common.domain.OrderInvoiceDO;


public interface OrderMapper {

    public Long countOrderInvoiceDetail(OrderInvoiceDO orderInvoiceDO);

    public List<OrderInvoiceDO> selectOrderInvoiceDetail(OrderInvoiceDO orderInvoiceDO);

    public Long countOrderList(OrderDO orderDO);

    public List<OrderDO> selectOrderList(OrderDO orderDO);

    public Long countThirdOrderList(OrderDO orderDO);

    public List<OrderDO> selectThirdOrderList(OrderDO orderDO);

    public List<OrderDO> getOrdersForBatch(OrderDO orderDO);

    public List<OrderInvoiceDO> selectFav(OrderInvoiceDO orderInvoiceDO);
    
    public OrderDO selectOrderById(OrderDO orderDO);
    
    public Long selectOrderRecordByTransNo(OrderDO orderDO);
    
    public int updateOrderById(OrderDO orderDO);
    
    public int updateOrderRecordById(Long pkChargingRecord);
}
