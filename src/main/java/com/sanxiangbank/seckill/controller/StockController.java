package com.sanxiangbank.seckill.controller;

import com.sanxiangbank.seckill.controller.RestResponseController.ResultData;
import com.sanxiangbank.seckill.controller.RestResponseController.ReturnCode;
import com.sanxiangbank.seckill.entity.Stock;
import com.sanxiangbank.seckill.entity.User;
import com.sanxiangbank.seckill.service.StockService;
import com.sanxiangbank.seckill.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StockController {
    @Autowired
    StockService stockService;

    @PostMapping("/stock/totalAlive")
    public List<Stock> totalAlive() {
        return stockService.getAliveStockList();
    }

    @PostMapping("/stock/total")
    public List<Stock> total() {
        return stockService.getStockList();
    }

    @PostMapping("/stock/profile")
    public ResultData profile(Stock stock) {
        log.info(stock.toString());
        return ResultData.success(stockService.add(stock));
    }

    @PostMapping("/stock/delete")
    public ResultData delete(@RequestParam int id) {
        log.info("要删除的stock id" + id);
        return ResultData.success(stockService.deleteStockByPrimaryKey(id));
    }

    /**
     * 查询库存：通过数据库查询库存
     *
     * @param sid
     * @return
     */
    @RequestMapping("/stock/getStockByDB/{sid}")
    @ResponseBody
    public String getStockByDB(@PathVariable int sid) {
        int count;
        try {
            count = stockService.getStockCountByDB(sid);
        } catch (Exception e) {
            log.error("查询库存失败：[{}]", e.getMessage());
            return "查询库存失败";
        }
        log.info("商品Id: [{}] 剩余库存为: [{}]", sid, count);
        return String.format("商品Id: %d 剩余库存为：%d", sid, count);
    }

    /**
     * 查询库存：通过缓存查询库存
     * 缓存命中：返回库存
     * 缓存未命中：查询数据库写入缓存并返回
     *
     * @param sid
     * @return
     */
    @RequestMapping("/stock/getStockByCache/{sid}")
    @ResponseBody
    public String getStockByCache(@PathVariable int sid) {
        Integer count;
        try {
            count = stockService.getStockCount(sid);
        } catch (Exception e) {
            log.error("查询库存失败：[{}]", e.getMessage());
            return "查询库存失败";
        }
        log.info("商品Id: [{}] 剩余库存为: [{}]", sid, count);
        return String.format("商品Id: %d 剩余库存为：%d", sid, count);
    }
}
