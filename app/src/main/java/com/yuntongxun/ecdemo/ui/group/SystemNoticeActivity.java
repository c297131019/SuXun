/*
 *  Copyright (c) 2013 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.yuntongxun.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package com.yuntongxun.ecdemo.ui.group;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yuntongxun.ecdemo.R;
import com.yuntongxun.ecdemo.common.dialog.ECProgressDialog;
import com.yuntongxun.ecdemo.common.utils.DateUtil;
import com.yuntongxun.ecdemo.common.utils.ImageLoader;
import com.yuntongxun.ecdemo.common.utils.LogUtil;
import com.yuntongxun.ecdemo.storage.FriendMessageSqlManager;
import com.yuntongxun.ecdemo.storage.GroupNoticeSqlManager;
import com.yuntongxun.ecdemo.storage.GroupSqlManager;
import com.yuntongxun.ecdemo.ui.CCPListAdapter;
import com.yuntongxun.ecdemo.ui.ECSuperActivity;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.im.ECAckType;
import com.yuntongxun.ecsdk.im.group.ECGroupNoticeMessage;

import java.util.Random;

import static com.tencent.bugly.crashreport.inner.InnerAPI.context;


/**
 * 系统通知接口
 *
 * @author Jorstin Chan@容联•云通讯
 * @version 4.0
 * @date 2014-12-31
 */
public class SystemNoticeActivity extends ECSuperActivity implements
        View.OnClickListener, GroupService.Callback {

    private static final String TAG = "ECDemo.SystemNoticeActivity";

    /**
     * 会话消息列表ListView
     */
    private ListView mListView;
    private GroupNoticeAdapter mAdapter;
    private ECProgressDialog mPostingdialog;

    @Override
    protected int getLayoutId() {
        return R.layout.group_notice_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        getTopBarView().setTopBarToStatus(1, R.drawable.topbar_back_bt, getString(R.string.app_clear), getString(R.string.app_title_notice), this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GroupService.addListener(this);
        GroupNoticeSqlManager.setAllSessionRead();
        GroupNoticeSqlManager.registerMsgObserver(mAdapter);
        mAdapter.notifyChange();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GroupNoticeSqlManager.unregisterMsgObserver(mAdapter);
    }


    /**
     *
     */
    private void initView() {
        if (mListView != null) {
            mListView.setAdapter(null);
        }

        mListView = (ListView) findViewById(R.id.group_notice_lv);
        View mCallEmptyView = findViewById(R.id.empty_conversation_tv);
        mListView.setEmptyView(mCallEmptyView);
        mListView.setDrawingCacheEnabled(false);
        mListView.setScrollingCacheEnabled(false);

        mListView.setOnItemClickListener(null);

        mAdapter = new GroupNoticeAdapter(this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onSyncGroup() {

    }

    @Override
    public void onSyncGroupInfo(String groupId) {

    }

    @Override
    public void onGroupDel(String groupId) {

    }

    @Override
    public void onError(ECError error) {
        dismissPostingDialog();
    }


    public class GroupNoticeAdapter extends CCPListAdapter<DemoGroupNotice> {
        private final int[] drawabs;

        /**
         * @param ctx
         */
        public GroupNoticeAdapter(Context ctx) {
            super(ctx, new DemoGroupNotice());

            drawabs = new int[]{R.drawable.qunzulist_icon_blue, R.drawable.qunzulist_icon_deepblue
                    , R.drawable.qunzulist_icon_green, R.drawable.qunzulist_icon_red};
        }

        @Override
        protected void initCursor() {
            notifyChange();
        }

        @Override
        protected DemoGroupNotice getItem(DemoGroupNotice t,
                                          Cursor cursor) {
            DemoGroupNotice message = new DemoGroupNotice();
            message.setCursor(cursor);
            return message;
        }

        public final CharSequence getContent(NoticeSystemMessage message) {
            if (message.getType() == ECGroupNoticeMessage.ECGroupMessageType.QUIT) {

            }
            return message.getContent();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view;
            ViewHolder mViewHolder;
            if (convertView == null || convertView.getTag() == null) {
                view = View.inflate(mContext, R.layout.group_notice_list_item, null);

                mViewHolder = new ViewHolder();
                mViewHolder.msgType = (TextView) view.findViewById(R.id.msg_type);
                mViewHolder.nickname = (TextView) view.findViewById(R.id.user_nickname);
                mViewHolder.ImageViewHeader = (ImageView) view.findViewById(R.id.ImageViewHeader);
                mViewHolder.msgTime = (TextView) view.findViewById(R.id.msg_time);
                mViewHolder.sysMsgFrom = (TextView) view.findViewById(R.id.sysMsg_from);
                mViewHolder.resultShow = (TextView) view.findViewById(R.id.result_show);
                mViewHolder.resultSummary = (TextView) view.findViewById(R.id.result_summary);
                mViewHolder.acceptBtn = (Button) view.findViewById(R.id.accept_btn);
                mViewHolder.refuseBtn = (Button) view.findViewById(R.id.Refuse_btn);
                mViewHolder.operationLy = (LinearLayout) view.findViewById(R.id.operation_ly);
                view.setTag(mViewHolder);
            } else {
                view = convertView;
                mViewHolder = (ViewHolder) view.getTag();
            }

            final DemoGroupNotice item = getItem(position);
            mViewHolder.nickname.setText(item.getGroupName());
            String content = item.getContent();
            if(item.getContent().contains("群管理员") && item.getContent().contains("邀请") && item.getContent().contains("群组")){
                content = "群管理员邀请了["+item.getNickName()+"]加入了群组";
            }
//            mViewHolder.resultSummary.setText(item.getContent());
            mViewHolder.resultSummary.setText(content);

            mViewHolder.sysMsgFrom.setText(getString(R.string.str_system_come_from, item.getGroupName()));
            mViewHolder.sysMsgFrom.setVisibility(View.GONE);

            if (!TextUtils.isEmpty(item.getMember())&&item.getDateCreate() <= 0) {//非群组
                String headUrl = FriendMessageSqlManager.queryURLByID(item.getMember());
                if (!TextUtils.isEmpty(headUrl)) {
                    ImageLoader.getInstance().displayCricleImage(context,headUrl, mViewHolder.ImageViewHeader);
                } else {
                    mViewHolder.ImageViewHeader.setImageResource(R.drawable.message_icon_header);
                }
            } else {
                mViewHolder.ImageViewHeader.setImageResource(drawabs[new Random().nextInt(drawabs.length)]);
            }
//            if(!TextUtils.isEmpty(item.getDeclared())) {
//                if(item.getAuditType() == ECGroupNoticeMessage.ECGroupMessageType.MODIFY_GROUP.ordinal()) {
//                    mViewHolder.sysMsgFrom.setText("附加消息：\r\n" + item.getDeclared());
//                } else {
//                    mViewHolder.sysMsgFrom.setText("附加消息：" + item.getDeclared());
//                }
//                mViewHolder.sysMsgFrom.setVisibility(View.VISIBLE);
//            }
            if (item.getDateCreate() > 0) {
                mViewHolder.msgTime.setText(DateUtil.getDateString(item.getDateCreate(), DateUtil.SHOW_TYPE_CALL_LOG));
            } else {
                mViewHolder.msgTime.setText("");
            }

            if (item.getConfirm() == GroupNoticeHelper.SYSTEM_MESSAGE_NEED_REPLAY) {

                // System information about the invitation to join the group
                // or join the group needs to operate, Whether is it right? Read or unread,
                // as long as the state has not operation can display the operating button
                mViewHolder.operationLy.setVisibility(View.VISIBLE);
                mViewHolder.resultShow.setVisibility(View.GONE);

            } else {
                // Other notice about information, only need to display
                // without the need to have relevant operation
                mViewHolder.operationLy.setVisibility(View.GONE);
                mViewHolder.resultShow.setVisibility(View.VISIBLE);
                if (item.getConfirm() == GroupNoticeHelper.SYSTEM_MESSAGE_REFUSE) {
                    mViewHolder.resultShow.setText(R.string.str_system_message_operation_result_refuse);
                } else if (item.getConfirm() == GroupNoticeHelper.SYSTEM_MESSAGE_THROUGH) {
                    mViewHolder.resultShow.setText(R.string.str_system_message_operation_result_through);

                } else {
                    mViewHolder.resultShow.setVisibility(View.GONE);
                }
            }


            mViewHolder.acceptBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //
                    OperationGroupSystemMsg(true, item);
                }
            });
            mViewHolder.refuseBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    OperationGroupSystemMsg(false, item);
                }
            });
            return view;
        }

        @Override
        protected void notifyChange() {
            Cursor cursor = GroupNoticeSqlManager.getCursor();
            setCursor(cursor);
            super.notifyDataSetChanged();
        }


        /**
         * 处理接受或者拒绝邀请
         *
         * @param isAccept
         * @param imSystemMessage
         */
        protected void OperationGroupSystemMsg(final boolean isAccept, final DemoGroupNotice imSystemMessage) {
            showProcessDialog(getString(R.string.login_posting_submit));

            synchronized (SystemNoticeActivity.class) {

                boolean isInvite = imSystemMessage.getAuditType() == ECGroupNoticeMessage.ECGroupMessageType.INVITE.ordinal();
                ECAckType ackType = isAccept ? ECAckType.AGREE : ECAckType.REJECT;
                GroupService.operationGroupApplyOrInvite(isInvite, imSystemMessage.getGroupId(), isInvite ? imSystemMessage.getAdmin() : imSystemMessage.getMember(), ackType, new GroupService.OnAckGroupServiceListener() {
                    @Override
                    public void onAckGroupService(boolean success) {
                        long rows = GroupNoticeSqlManager.updateNoticeOperation(imSystemMessage.getId(), isAccept);
                        LogUtil.d(TAG, "[OperationGroupSystemMsg] result :" + rows + " ,");

                        if (success) {
                            GroupSqlManager.updateJoinStatus(imSystemMessage.getGroupId(), isAccept);
                        }

                        notifyChange();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dismissPostingDialog();
                            }
                        });
                    }
                });
            }
        }

    }


    static class ViewHolder {
        LinearLayout operationLy;
        TextView msgType;
        TextView resultShow;
        TextView nickname;
        TextView sysMsgFrom;
        TextView msgTime;
        ImageView ImageViewHeader;
        TextView resultSummary;
        Button acceptBtn; // accetp
        Button refuseBtn;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.closeCursor();
        }
        GroupService.addListener(null);
        System.gc();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                hideSoftKeyboard();
                finish();
                break;
            case R.id.text_right:
                GroupNoticeSqlManager.delSessions();
                mAdapter.notifyChange();
                break;
            default:
                break;
        }
    }


    void showProcessDialog(String tips) {
        mPostingdialog = new ECProgressDialog(SystemNoticeActivity.this, R.string.login_posting_submit);
        mPostingdialog.show();
    }

    /**
     * 关闭对话框
     */
    private void dismissPostingDialog() {
        if (mPostingdialog == null || !mPostingdialog.isShowing()) {
            return;
        }
        mPostingdialog.dismiss();
        mPostingdialog = null;
    }

    @Override
    public void onUpdateGroupAnonymitySuccess(String groupId,
                                              boolean isAnonymity) {
        // TODO Auto-generated method stub

    }


}
