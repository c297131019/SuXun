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
package com.yuntongxun.ecdemo.ui;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yuntongxun.ecdemo.R;
import com.yuntongxun.ecdemo.common.CCPAppManager;
import com.yuntongxun.ecdemo.common.utils.DemoUtils;
import com.yuntongxun.ecdemo.storage.GroupSqlManager;
import com.yuntongxun.ecdemo.storage.IMessageSqlManager;
import com.yuntongxun.ecdemo.ui.chatting.base.EmojiconTextView;
import com.yuntongxun.ecdemo.ui.contact.ContactLogic;
import com.yuntongxun.ecdemo.ui.group.ApplyWithGroupPermissionActivity;
import com.yuntongxun.ecdemo.ui.group.DemoGroup;
import com.yuntongxun.ecdemo.ui.group.GroupInfoActivity;
import com.yuntongxun.ecdemo.ui.group.GroupService;
import com.yuntongxun.ecsdk.ECError;

import java.util.Random;

/**
 * 讨论组列表界面
 *
 * @author 容联•云通讯
 * @version 4.0
 * @date 2014-12-4
 */
public class DiscussionListFragment extends TabFragment implements
        GroupService.Callback {

    /**
     * 讨论组列表
     */
    private ListView mListView;
    /**
     * 讨论组列表信息适配器
     */
    private GroupAdapter mGroupAdapter;
    private boolean sync = false;

    boolean isDiscussion = false;
    /**
     * 讨论组列表点击事件
     */
    private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            if (mGroupAdapter != null) {
                DemoGroup dGroup = mGroupAdapter.getItem(position);
                if (dGroup.isJoin()) {
                    CCPAppManager.startChattingAction(getActivity(),
                            dGroup.getGroupId(), dGroup.getName());
                    return;
                }
                Intent intent = new Intent(getActivity(),
                        ApplyWithGroupPermissionActivity.class);
                intent.putExtra(GroupInfoActivity.GROUP_ID, dGroup.getGroupId());
                startActivity(intent);
            }
        }

    };

    private boolean isRefresh = false;


    public static DiscussionListFragment newInstance() {
        DiscussionListFragment fragment = new DiscussionListFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mListView != null) {
            mListView.setAdapter(null);
        }
        mListView = (ListView) getActivity().findViewById(R.id.group_list_dis);
        LinearLayout emptyView = (LinearLayout) getActivity().findViewById(R.id.ll_empty);

        mListView.setEmptyView(emptyView);
        mListView.setOnItemClickListener(onItemClickListener);
        mGroupAdapter = new GroupAdapter(getActivity());
        mListView.setAdapter(mGroupAdapter);

        getActivity().findViewById(R.id.loading_tips_area).setVisibility(View.GONE);


    }

    public void onDisGroupFragmentVisible(boolean visible) {

        if (visible && isVisible() && !isRefresh) {
            onSyncGroup();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        GroupSqlManager.registerGroupObserver(mGroupAdapter);
        mGroupAdapter.notifyChange();

        registerReceiver(new String[]{
                getActivity().getPackageName() + ".inited",
                IMessageSqlManager.ACTION_GROUP_DEL});

        if (!sync) {
            GroupService.syncDiscussionGroup(this);
            sync = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        GroupSqlManager.unregisterGroupObserver(mGroupAdapter);


    }

    @Override
    protected void handleReceiver(Context context, Intent intent) {
        if (intent.getAction().equals(
                new String[]{getActivity().getPackageName() + ".inited"})) {
            GroupService.syncGroup(this);
        } else if (IMessageSqlManager.ACTION_GROUP_DEL.equals(intent
                .getAction())) {
            onSyncGroup();
        }
    }


    public class GroupAdapter extends CCPListAdapter<DemoGroup> {
        int padding;
        private final int[] drawables;

        /**
         * @param ctx
         */
        public GroupAdapter(Context ctx) {
            super(ctx, new DemoGroup());
            padding = ctx.getResources().getDimensionPixelSize(
                    R.dimen.OneDPPadding);

            drawables = new int[]{R.drawable.taolunzulist_icon_taolun_green,R.drawable.taolunzulist_icon_taolun_red
            ,R.drawable.taolunzulist_icon_taolun_blue,R.drawable.taolunzulist_icon_taolun_deepblue};
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            ViewHolder mViewHolder = null;
            if (convertView == null || convertView.getTag() == null) {
                view = View.inflate(mContext, R.layout.group_item, null);

                mViewHolder = new ViewHolder();
                mViewHolder.groupitem_avatar_iv = (ImageView) view
                        .findViewById(R.id.groupitem_avatar_iv);

                mViewHolder.group_name = (EmojiconTextView) view
                        .findViewById(R.id.group_name);
                mViewHolder.group_id = (TextView) view
                        .findViewById(R.id.group_id);
                mViewHolder.join_state = (TextView) view
                        .findViewById(R.id.join_state);
                view.setTag(mViewHolder);
            } else {
                view = convertView;
                mViewHolder = (ViewHolder) view.getTag();
            }

            DemoGroup group = getItem(position);
            if (group != null) {

                mViewHolder.groupitem_avatar_iv
                        .setImageResource(drawables[new Random().nextInt(drawables.length)]);

                mViewHolder.group_name.setText(TextUtils.isEmpty(group
                        .getName()) ? group.getGroupId() : group.getName());
                mViewHolder.group_id.setText(getString(
                        R.string.str_dis_id_fmt,
                        DemoUtils.getGroupShortId(group.getGroupId())));
                mViewHolder.join_state.setText(group.isJoin() ? "已加入" : "");
                mViewHolder.join_state
                        .setVisibility(group.isJoin() ? View.VISIBLE
                                : View.GONE);
            }

            return view;
        }

        @Override
        protected void notifyChange() {
            Cursor cursor = GroupSqlManager.getGroupCursor(false);
            setCursor(cursor);
            super.notifyDataSetChanged();
        }

        @Override
        protected void initCursor() {
            notifyChange();
        }

        @Override
        protected DemoGroup getItem(DemoGroup t, Cursor cursor) {
            DemoGroup group = new DemoGroup();
            group.setCursor(cursor);
            return group;
        }

        class ViewHolder {
            ImageView groupitem_avatar_iv;
            EmojiconTextView group_name;
            TextView group_id;
            TextView join_state;
        }

    }

    @Override
    public void onSyncGroup() {
        mGroupAdapter.notifyChange();
        isRefresh = true;
    }

    @Override
    public void onSyncGroupInfo(String groupId) {

    }

    @Override
    public void onGroupDel(String groupId) {
        onSyncGroup();
    }

    @Override
    public void onError(ECError error) {
    }


    @Override
    public void onUpdateGroupAnonymitySuccess(String groupId,
                                              boolean isAnonymity) {

    }


    @Override
    protected void onTabFragmentClick() {
        // TODO Auto-generated method stub

    }


    @Override
    protected void onReleaseTabUI() {
        // TODO Auto-generated method stub

    }


    @Override
    protected int getLayoutId() {
        // TODO Auto-generated method stub
        return R.layout.groups_activity_discussion;
    }


}
