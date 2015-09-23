module.exports = function(sequelize, DataTypes) {
  var Tag = sequelize.define('Tag', {
    title: {
      type: DataTypes.STRING,
      allowNull: false
    }
  });

  return Tag;
}