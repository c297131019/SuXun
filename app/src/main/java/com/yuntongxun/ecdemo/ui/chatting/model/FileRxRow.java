/*
 *  Copyright (c) 2013 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.cloopen.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package com.yuntongxun.ecdemo.ui.chatting.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.yuntongxun.ecdemo.R;
import com.yuntongxun.ecdemo.common.utils.DemoUtils;
import com.yuntongxun.ecdemo.common.utils.FileAccessor;
import com.yuntongxun.ecdemo.common.utils.FileUtils;
import com.yuntongxun.ecdemo.storage.IMessageSqlManager;
import com.yuntongxun.ecdemo.ui.chatting.ChattingActivity;
import com.yuntongxun.ecdemo.ui.chatting.holder.BaseHolder;
import com.yuntongxun.ecdemo.ui.chatting.holder.FileRowViewHolder;
import com.yuntongxun.ecdemo.ui.chatting.view.ChattingItemContainer;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.ECMessage.Type;
import com.yuntongxun.ecsdk.im.ECFileMessageBody;
import com.yuntongxun.ecsdk.im.ECVideoMessageBody;

import java.io.File;

/**接收
 * <p>
 * Title: FileRxRow.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Beijing Speedtong Information Technology Co.,Ltd
 * </p>
 * 
 * @author Jorstin Chan
 * @date 2014-4-17
 * @version 1.0
 */
public class FileRxRow extends BaseChattingRow {

	public FileRxRow(int type) {
		super(type);
	}

	@Override
	public View buildChatView(LayoutInflater inflater, View convertView) {
		// we have a don't have a converView so we'll have to create a new one
		if (convertView == null || convertView.getTag() == null) {
			convertView = new ChattingItemContainer(inflater,
					R.layout.chatting_item_file_from);

			// use the view holder pattern to save of already looked up subviews
			FileRowViewHolder holder = new FileRowViewHolder(mRowType);
			convertView.setTag(holder.initBaseHolder(convertView, true));
		}
		return convertView;
	}

	@Override
	public void buildChattingData(final Context context, BaseHolder baseHolder,
			ECMessage detail, int position) {
		FileRowViewHolder holder = (FileRowViewHolder) baseHolder;
		OnClickListener onClickListener = ((ChattingActivity) context).mChattingFragment
				.getChattingAdapter().getOnClickListener();
		ViewHolderTag holderTag = ViewHolderTag.createTag(detail,
				ViewHolderTag.TagType.TAG_VIEW_FILE, position);
		if (detail != null) {
			ECMessage msg = detail;
			final ECFileMessageBody body = (ECFileMessageBody) msg.getBody();
			holder.contentTv.setText(body.getFileName());
			String fileName = "";
			String userData = msg.getUserData();

			if (TextUtils.isEmpty(userData)) {
			} else {
				fileName = userData.substring(userData.indexOf("fileName=")
						+ "fileName=".length(), userData.length());
			}
			if ("mp4".equals(FileUtils.getFileExt(fileName))&&detail.getType()==Type.VIDEO) {
				ECVideoMessageBody videoBody = (ECVideoMessageBody) msg
						.getBody();
				holder.contentTv.setVisibility(View.GONE);
				holder.contentTv.setTag(null);
				holder.contentTv.setOnClickListener(null);
				holder.fl.setVisibility(View.VISIBLE);

				holder.ivVideoMp4.setVisibility(View.VISIBLE);
				holder.buPlayVideo.setOnClickListener(onClickListener);
				holder.buPlayVideo.setTag(holderTag);

				holder.tvFile.setVisibility(View.VISIBLE);

				String text = IMessageSqlManager.qureyVideoMsgLength(msg
						.getMsgId());

				if (!TextUtils.isEmpty(text)) {
					holder.tvFile.setText(DemoUtils.bytes2kb(Long.parseLong(text)));
				}
				File file = new File(FileAccessor.getFilePathName(),
						body.getFileName() + "_thum.png");
				final String path = FileAccessor.getFilePathName()+"/"+body.getFileName();

				if (file.exists()) {
					Bitmap thumbBitmap = DemoUtils.getSuitableBitmap(file.getAbsolutePath());
					if(thumbBitmap!=null){
						holder.ivVideoMp4.setImageBitmap(thumbBitmap);
					}
				}

//				File f  = new File(path);
//				if(f.exists()&&f.length()>0) {
//					new Thread(new Runnable() {
//						@Override
//						public void run() {
//							SmallVideoHelper.getInstance().decoder(path);
//						}
//					}).start();
//
//					AnimationDrawable drawable = SmallVideoHelper.getInstance().get(path);
//					if (drawable != null) {
//						drawable.setOneShot(false);
//						holder.ivVideoMp4.setImageDrawable(drawable);
//						drawable.start();
//					}
//				}


			} else {
				holder.contentTv.setVisibility(View.VISIBLE);
				holder.ivVideoMp4.setVisibility(View.GONE);
				holder.fl.setVisibility(View.GONE);
				holder.buPlayVideo.setTag(null);
				holder.buPlayVideo.setOnClickListener(null);
				holder.contentTv.setTag(holderTag);
				holder.contentTv.setOnClickListener(onClickListener);
				holder.tvFile.setVisibility(View.GONE);
			}
			getMsgStateResId(position, holder, detail, onClickListener);

		}
	}

	@Override
	public int getChatViewType() {
		return ChattingRowType.FILE_ROW_RECEIVED.ordinal();
	}

	@Override
	public boolean onCreateRowContextMenu(ContextMenu contextMenu,
			View targetView, ECMessage detail) {
		// TODO Auto-generated method stub
		return false;
	}

}
