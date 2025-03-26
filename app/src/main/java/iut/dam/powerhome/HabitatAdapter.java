package iut.dam.powerhome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import iut.dam.powerhome.model.Appliance;
import iut.dam.powerhome.model.Habitat;

public class HabitatAdapter extends ArrayAdapter<Habitat> {

    public HabitatAdapter(Context context, List<Habitat> habitats) {
        super(context, 0, habitats);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Habitat habitat = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.items_habitat, parent, false);
        }

        TextView residentNameText = convertView.findViewById(R.id.residentNameText);
        TextView numEtage = convertView.findViewById(R.id.numEtage);
        TextView nbEquip = convertView.findViewById(R.id.nbEquipement);

        assert habitat != null;
        residentNameText.setText(habitat.getResidentName());
        numEtage.setText("Étage : " + habitat.getFloor());
        nbEquip.setText(habitat.getAppliances().size() + " équipement(s)");


        LinearLayout equipmentIcons = convertView.findViewById(R.id.equipmentIcons);
        equipmentIcons.removeAllViews(); // important : réutilisation de vues

        for (Appliance app : habitat.getAppliances()) {
            ImageView icon = new ImageView(getContext());
            icon.setImageResource(getIconForAppliance(app));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    80, 80); // taille icône
            params.setMargins(8, 0, 8, 0); // espacement

            icon.setLayoutParams(params);
            equipmentIcons.addView(icon);
        }



        return convertView;
    }

    private int getIconForAppliance(Appliance appliance) {
        String ref = appliance.getReference().toLowerCase();

        if (ref.contains("clim")) return R.drawable.airconditioning;
        if (ref.contains("fer")) return R.drawable.iron;
        if (ref.contains("aspir")) return R.drawable.vacuum;
        if (ref.contains("ll")) return R.drawable.washingmachine;

        else
            return R.drawable.noappliance;
    }
}
