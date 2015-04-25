package cn.edu.jmu.jyf.responseModel;

import cn.edu.jmu.jyf.model.ProfileModel;

public class ProfileResponse extends ResponseModel {
	private ProfileModel profileModel;

	public ProfileModel getProfileModel() {
		return profileModel;
	}

	public void setProfileModel(ProfileModel profileModel) {
		this.profileModel = profileModel;
	}

	public ProfileResponse(String responseCode, ProfileModel profileModel) {
		super(responseCode);
		this.profileModel = profileModel;
	}

}
