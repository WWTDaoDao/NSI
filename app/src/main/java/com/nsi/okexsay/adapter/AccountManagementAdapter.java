package com.nsi.okexsay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.serializer.BeanContext;
import com.nsi.okexsay.Beans.AccountManagementBean;
import com.nsi.okexsay.R;

import java.util.List;

public class AccountManagementAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<AccountManagementBean> list_data;
    private LayoutInflater inflater;
public String index="";
    public AccountManagementAdapter(Context context, List list_data) {
        this.context = context;
        this.list_data = list_data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list_data.size()) {
            return 126;
        }
        return list_data.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case 123:
                //银行卡
                return new BankView(inflater.inflate(R.layout.item_account_management, viewGroup, false));
            case 125:
                //支付宝
                return new ZhiFuBaoView(inflater.inflate(R.layout.item_account_management, viewGroup, false));
            case 124:
                //微信
                return new WeiXinView(inflater.inflate(R.layout.item_account_management, viewGroup, false));
            case 126:
                //添加按钮
                return new AddView(inflater.inflate(R.layout.item_add_type_view, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (i == list_data.size()) {
            return;
        }
        int itemType = list_data.get(i).getType();
        switch (itemType) {
            case 123:
                //银行卡
                ((BankView) viewHolder).bindData(list_data.get(i));
                break;
            case 125:
                //支付宝
                ((ZhiFuBaoView) viewHolder).bindData(list_data.get(i));
                break;
            case 124:
                //微信
                ((WeiXinView) viewHolder).bindData(list_data.get(i));
                break;
            case 126:

                break;
        }
    }

    @Override
    public int getItemCount() {
        return list_data.size() + 1;
    }

    class BankView extends RecyclerView.ViewHolder {

        private TextView idName;
        private TextView idCode;
        private TextView idDelete;

        public BankView(@NonNull View itemView) {
            super(itemView);

            idName = (TextView) itemView.findViewById(R.id.id_name);
            idCode = (TextView) itemView.findViewById(R.id.id_code);
            idDelete = (TextView) itemView.findViewById(R.id.id_delete);

        }

        public void bindData(final AccountManagementBean accountManagementBean) {
            idName.setText(accountManagementBean.getTypefrom());
            idCode.setText(accountManagementBean.getNumber());
            idDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClick != null) {
                        index=accountManagementBean.getNumber();//获取所在账号
                        onClick.delete(accountManagementBean);
                    }
                }
            });
        }
    }

    class ZhiFuBaoView extends RecyclerView.ViewHolder {

        private TextView idName;
        private TextView idCode;
        private TextView idDelete;

        public ZhiFuBaoView(@NonNull View itemView) {
            super(itemView);

            idName = (TextView) itemView.findViewById(R.id.id_name);
            idCode = (TextView) itemView.findViewById(R.id.id_code);
            idDelete = (TextView) itemView.findViewById(R.id.id_delete);

        }

        public void bindData(final AccountManagementBean accountManagementBean) {
            idName.setText(accountManagementBean.getTypefrom());
            idCode.setText(accountManagementBean.getNumber());

            idDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClick != null) {
                        index=accountManagementBean.getNumber();//获取所在账号
                        onClick.delete(accountManagementBean);
                    }
                }
            });
        }
    }

    class WeiXinView extends RecyclerView.ViewHolder {

        private TextView idName;
        private TextView idCode;
        private TextView idDelete;

        public WeiXinView(@NonNull View itemView) {
            super(itemView);

            idName = (TextView) itemView.findViewById(R.id.id_name);
            idCode = (TextView) itemView.findViewById(R.id.id_code);
            idDelete = (TextView) itemView.findViewById(R.id.id_delete);


        }

        public void bindData(final AccountManagementBean accountManagementBean) {

            idName.setText(accountManagementBean.getTypefrom());
            idCode.setText(accountManagementBean.getNumber());
            idDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClick != null) {
                        index=accountManagementBean.getNumber();//获取所在账号
                        onClick.delete(accountManagementBean);
                    }
                }
            });
        }
    }

    class AddView extends RecyclerView.ViewHolder {

        public AddView(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClick != null) {
                        onClick.add();
                    }
                }
            });
        }
    }


    public interface OnClick {
        void delete(AccountManagementBean accountManagementBean);

        void click(AccountManagementBean accountManagementBean);

        void add();
    }

    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

}
