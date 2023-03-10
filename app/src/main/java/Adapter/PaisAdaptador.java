package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demh.recyclerviewpais.R;

import java.util.List;

import Models.Pais;

public class PaisAdaptador extends RecyclerView.Adapter<PaisAdaptador.PaisViewHolder> {

    private Context Ctx;
    private List<Pais> lstPaises;

    public PaisAdaptador(Context mCtx, List<Pais> paises) {
        this.lstPaises = paises;
        Ctx = mCtx;

    }

    @Override
    public PaisAdaptador.PaisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.layoutpais, null);
        return new PaisAdaptador.PaisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaisAdaptador.PaisViewHolder holder, int position) {
        Pais paises = lstPaises.get(position);
        holder.txtTituloPais.setText(paises.getTxtTituloPais());
        holder.txtCapital.setText(paises.getTxtCapital());
        holder.txtPrefijo.setText(paises.getTxtPrefijo());
        holder.txtInfocountry.setText(paises.getInfocountry());
        Glide.with(Ctx)
                .load(paises.getImgUrlBandera())
                .into(holder.imgUrlBandera);
    }

    @Override
    public int getItemCount() {
        return lstPaises.size();
    }

    class PaisViewHolder extends RecyclerView.ViewHolder {
        TextView txtTituloPais, txtCapital, txtPrefijo, txtInfocountry;
        ImageView imgUrlBandera;
        public PaisViewHolder(View itemView) {
            super(itemView);
            txtTituloPais = itemView.findViewById(R.id.txtTituloPais);
            txtCapital = itemView.findViewById(R.id.txtCapital);
            txtPrefijo = itemView.findViewById(R.id.txtPrefijo);
            txtInfocountry = itemView.findViewById(R.id.txtInfocountry);
            imgUrlBandera = itemView.findViewById(R.id.imgUrlBandera);
        }
    }
}
