package uwi.comp6901.klbakery.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import uwi.comp6901.klbakery.db.BakeryDatabase;
import uwi.comp6901.klbakery.db.dao.OrderDetailDao;
import uwi.comp6901.klbakery.db.entity.OrderDetail;

public class OrderDetailRepository {
    private OrderDetailDao orderDetailDao;

    public OrderDetailRepository(Application application){
        BakeryDatabase db = BakeryDatabase.getInstance(application);
        orderDetailDao = db.orderDetailDao();
    }

    public void insert(OrderDetail orderDetail){
        new InsertOrderDetailAsyncTask(orderDetailDao).execute(orderDetail);
    }

    public void update(OrderDetail orderDetail){
        new UpdateOrderDetailAsyncTask(orderDetailDao).execute(orderDetail);
    }

    public void delete(OrderDetail orderDetail){
        new DeleteOrderDetailAsyncTask(orderDetailDao).execute(orderDetail);
    }

    public LiveData<OrderDetail> loadOrderDetail(int orderDetailId){
        return orderDetailDao.loadOrderDetail(orderDetailId);
    }

    public LiveData<List<OrderDetail>> allOrderDetails(int orderId){
        return orderDetailDao.allOrderDetails(orderId);
    }

    private static class InsertOrderDetailAsyncTask extends AsyncTask<OrderDetail, Void, Void>{
        private OrderDetailDao orderDetailDao;

        private InsertOrderDetailAsyncTask(OrderDetailDao orderDetailDao){
            this.orderDetailDao = orderDetailDao;
        }

        @Override
        protected Void doInBackground(OrderDetail... orderDetails){
            orderDetailDao.insert(orderDetails[0]);
            return null;
        }

    }

    private static class UpdateOrderDetailAsyncTask extends AsyncTask<OrderDetail, Void, Void>{
        private OrderDetailDao orderDetailDao;

        private UpdateOrderDetailAsyncTask(OrderDetailDao orderDetailDao){
            this.orderDetailDao = orderDetailDao;
        }

        @Override
        protected Void doInBackground(OrderDetail... orderDetails){
            orderDetailDao.update(orderDetails[0]);
            return null;
        }

    }

    private static class DeleteOrderDetailAsyncTask extends AsyncTask<OrderDetail, Void, Void>{
        private OrderDetailDao orderDetailDao;

        private DeleteOrderDetailAsyncTask(OrderDetailDao orderDetailDao){
            this.orderDetailDao = orderDetailDao;
        }

        @Override
        protected Void doInBackground(OrderDetail... orderDetails){
            orderDetailDao.delete(orderDetails[0]);
            return null;
        }

    }
}
