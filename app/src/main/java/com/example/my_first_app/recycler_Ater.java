package com.example.my_first_app;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class recycler_Ater extends RecyclerView.Adapter<recycler_Ater.ViewHolder>{
    ArrayList<struct>a1,fllist;
     Context c1;
    public recycler_Ater(ArrayList<struct>a1,Context c1)
    {
        this.a1=a1;
        this.c1=c1;
        this.fllist=new ArrayList<>(a1);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        return new ViewHolder(v);
    }
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
          holder.i1.setImageResource(fllist.get(position).img);
          holder.t1.setText(fllist.get(position).group);
          holder.t2.setText(fllist.get(position).message);

        Bitmap bit=vectorToBitmap(c1,fllist.get(position).img);
        Palette.from(bit).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int def=Color.WHITE;
                int dominant=palette.getDominantColor(def);
                int vibrant =palette.getVibrantColor(def);
                GradientDrawable grd = (GradientDrawable) ContextCompat.getDrawable(c1, R.drawable.whatsapp_image_background);
                if(grd!=null) {
                    grd.setColor(dominant);
                    holder.i1.setBackground(grd);
                }


            }
        });


          holder.i1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Dialog dlg1=new Dialog(c1);//recycler_Ater is an ArrayAdapter to iska context pass nhi hoga
                                              //c1 iska context pass hoga
                  dlg1.setContentView(R.layout.dialog_background);
                  ImageView we1=dlg1.findViewById(R.id.smoo);
                  we1.setImageResource(fllist.get(position).img);
                  LinearLayout llay1= dlg1.findViewById(R.id.lin);
//                  llay1.setBackgroundColor(Color.RED);
//                  if(a1.get(position).group.equals("Police Uncle"))
//                  {
//                      we1.setBackgroundColor(Color.RED);//Isse khali Image ka Background Color set hoga pr
                  //hume pure dialog box ka set karwana hai to is
//                  }
                  Log.d("DEBUG", "Image ID: " + fllist.get(position).img);
                  //Palette API Usage
//                  Bitmap btmap= BitmapFactory.decodeResource(c1.getResources(),a1.get(position).img);
                  Bitmap btmap = vectorToBitmap(c1, fllist.get(position).img);
                  if(btmap!=null  && !btmap.isRecycled()) {
                      Palette.from(btmap).generate(new Palette.PaletteAsyncListener() {
                          @Override
                          public void onGenerated(@Nullable Palette palette) {
                              int def = Color.WHITE;

                              int dominant = palette.getDominantColor(def);
                              int muted = palette.getMutedColor(def);
                              int vibrant = palette.getVibrantColor(def);
                              int lightMuted = palette.getLightMutedColor(def);
                              int darkMuted = palette.getDarkMutedColor(def);
                              llay1.setBackgroundColor(dominant);
                          }
                      });
                  }
                  else
                  {
                      llay1.setBackgroundColor(Color.WHITE);
                  }
                  dlg1.show();
              }
          });
    }
    @Override
    public int getItemCount() {
        return fllist.size();
    }
  public static class ViewHolder extends RecyclerView.ViewHolder
  {
      ImageView i1;
      TextView t1,t2;
      public ViewHolder(@NonNull View itemView) {
          super(itemView);
          i1=itemView.findViewById(R.id.smoo);
          t1=itemView.findViewById(R.id.txt1);
          t2=itemView.findViewById(R.id.txt2);
      }
  }
  public void filt(String text)
  {
      fllist.clear();
      if(text.isEmpty())
      {
          fllist.addAll(a1);
      }
      else
      {
         text=text.toLowerCase();
         for(struct it:a1)
         {
             if(it.group.toLowerCase().contains(text))
             {
                 fllist.add(it);
             }

         }
      }
   notifyDataSetChanged();
  }

    @SuppressLint("UseCompatLoadingForDrawables")
    public Bitmap vectorToBitmap(Context context, Object drawableId) {
        if (drawableId instanceof Integer) {
            Drawable drawable = context.getDrawable((Integer) drawableId);
            if (drawable == null) return null;
            int width = drawable.getIntrinsicWidth() > 0 ? drawable.getIntrinsicWidth() : 100;
            int height = drawable.getIntrinsicHeight() > 0 ? drawable.getIntrinsicHeight() : 100;
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }
        else if(drawableId instanceof ImageView)
        {
            Drawable drawable=((ImageView) drawableId).getDrawable();
            if (drawable == null) return null;
            int width = drawable.getIntrinsicWidth() > 0 ? drawable.getIntrinsicWidth() : 100;
            int height = drawable.getIntrinsicHeight() > 0 ? drawable.getIntrinsicHeight() : 100;
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }

        return null;
    }
    }







