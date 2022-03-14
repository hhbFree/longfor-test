package com.longfor.shopping.common.service;

import com.longfor.shopping.common.design.chain.dto.ChainParam;
import com.longfor.shopping.common.design.chain.dto.ChainResult;

public interface OrderService {

    ChainResult order(ChainParam param);
}
