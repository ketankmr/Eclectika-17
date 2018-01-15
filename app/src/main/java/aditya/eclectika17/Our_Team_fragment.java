package aditya.eclectika17;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class Our_Team_fragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ourteam_layout, container, false);

      ImageView img1 = (ImageView) view.findViewById(R.id.image1);
        ImageView img2 = (ImageView) view.findViewById(R.id.image2);
        ImageView img3 = (ImageView) view.findViewById(R.id.image3);
        ImageView img4 = (ImageView) view.findViewById(R.id.image4);
        ImageView img5 = (ImageView) view.findViewById(R.id.image5);
        ImageView img6 = (ImageView) view.findViewById(R.id.image6);
        ImageView img7= (ImageView) view.findViewById(R.id.image7);
        ImageView img8 = (ImageView) view.findViewById(R.id.image8);
        ImageView img9 = (ImageView) view.findViewById(R.id.image9);
        ImageView img10 = (ImageView) view.findViewById(R.id.image10);
        ImageView img11 = (ImageView) view.findViewById(R.id.image11);

        Glide
                .with(getActivity())
                .load("http://eclectika.org/assets/img/team/adarsh.jpg")
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .crossFade()
                .centerCrop()
                .into(img1);

        Glide
                .with(getActivity())
                .load("http://eclectika.org/assets/img/team/ragashree.jpg")
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .crossFade()
                .centerCrop()
                .into(img2);

        Glide
                .with(getActivity())
                .load("http://eclectika.org/assets/img/team/aditya.jpg")
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .crossFade()
                .centerCrop()
                .into(img3);

        Glide
                .with(getActivity())
                .load("http://eclectika.org/assets/img/team/nandu.jpg")
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .crossFade()
                .centerCrop()
                .into(img4);

        Glide
                .with(getActivity())
                .load("http://eclectika.org/assets/img/team/chanda-sir.jpg")
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .crossFade()
                .centerCrop()
                .into(img5);

        Glide
                .with(getActivity())
                .load("http://eclectika.org/assets/img/team/anamika.jpg")
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .crossFade()
                .centerCrop()
                .into(img6);

        Glide
                .with(getActivity())
                .load("http://eclectika.org/assets/img/team/shashi.jpg")
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .crossFade()
                .centerCrop()
                .into(img7);

        Glide
                .with(getActivity())
                .load("http://eclectika.org/assets/img/team/alekhya.jpg")
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .crossFade()
                .centerCrop()
                .into(img8);

        Glide
                .with(getActivity())
                .load("http://eclectika.org/assets/img/team/yugesh.jpg")
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .crossFade()
                .centerCrop()
                .into(img9);

        Glide
                .with(getActivity())
                .load("http://eclectika.org/assets/img/team/pillai.jpg")
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .crossFade()
                .centerCrop()
                .into(img10);

        Glide
                .with(getActivity())
                .load("http://eclectika.org/assets/img/team/lakshya.jpg")
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .crossFade()
                .centerCrop()
                .into(img11);


        return view ;
    }


}
